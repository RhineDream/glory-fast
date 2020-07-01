package top.glory.modules.system.entity;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 树形数据构造
 *
 * @param <PK> 实体主键类型
 */
public interface TreeSupportEntity<PK> {

    PK getId();

    PK getParentId();

    /**
     * 集合转为树形结构,返回根节点集合
     *
     * @param dataList      需要转换的集合
     * @param childConsumer 设置子节点回调
     * @param <N>           树节点类型
     * @param <PK>          主键类型
     * @return 树形结构集合
     */
    static <N extends TreeSupportEntity<PK>, PK> List<N> list2tree(Collection<N> dataList, BiConsumer<N, List<N>> childConsumer) {
        return list2tree(dataList, childConsumer, (Function<TreeHelper<N, PK>, Predicate<N>>) predicate -> node -> node == null || predicate.getNode(node.getParentId()) == null);
    }

    /**
     * 列表结构转为树结构,并返回根节点集合
     *
     * @param dataList          数据集合
     * @param childConsumer     子节点消费接口,用于设置子节点
     * @param rootNodePredicate 判断是否为跟节点的函数
     * @param <N>               元素类型
     * @param <PK>              主键类型
     * @return 根节点集合
     */
    static <N extends TreeSupportEntity<PK>, PK> List<N> list2tree(Collection<N> dataList,
                                                                                         BiConsumer<N, List<N>> childConsumer,
                                                                                         Predicate<N> rootNodePredicate) {
        return list2tree(dataList, childConsumer, (Function<TreeHelper<N, PK>, Predicate<N>>) predicate -> rootNodePredicate);
    }

    /**
     * 列表结构转为树结构,并返回根节点集合
     *
     * @param dataList          数据集合
     * @param childConsumer     子节点消费接口,用于设置子节点
     * @param predicateFunction 根节点判断函数,传入helper,获取一个判断是否为跟节点的函数
     * @param <N>               元素类型
     * @param <PK>              主键类型
     * @return 根节点集合
     */
    static <N extends TreeSupportEntity<PK>, PK> List<N> list2tree(final Collection<N> dataList,
                                                                                         final BiConsumer<N, List<N>> childConsumer,
                                                                                         final Function<TreeHelper<N, PK>, Predicate<N>> predicateFunction) {
        Objects.requireNonNull(dataList, "source list can not be null");
        Objects.requireNonNull(childConsumer, "child consumer can not be null");
        Objects.requireNonNull(predicateFunction, "root predicate function can not be null");

        Supplier<Stream<N>> streamSupplier = () -> dataList.size() < 1000 ? dataList.stream() : dataList.parallelStream();
        // id,node
        Map<PK, N> cache = new HashMap<>();
        // parentId,children
        Map<PK, List<N>> treeCache = streamSupplier.get()
                .peek(node -> cache.put(node.getId(), node))
                .collect(Collectors.groupingBy(TreeSupportEntity::getParentId));

        Predicate<N> rootNodePredicate = predicateFunction.apply(new TreeHelper<N, PK>() {
            @Override
            public List<N> getChildren(PK parentId) {
                return treeCache.get(parentId);
            }

            @Override
            public N getNode(PK id) {
                return cache.get(id);
            }
        });

        return streamSupplier.get()
                //设置每个节点的子节点
                .peek(node -> childConsumer.accept(node, treeCache.get(node.getId())))
                //获取根节点
                .filter(rootNodePredicate)
                .collect(Collectors.toList());
    }

    /**
     * 树结构Helper
     *
     * @param <T>  节点类型
     * @param <PK> 主键类型
     */
    interface TreeHelper<T, PK> {
        /**
         * 根据主键获取子节点
         *
         * @param parentId 节点ID
         * @return 子节点集合
         */
        List<T> getChildren(PK parentId);

        /**
         * 根据id获取节点
         *
         * @param id 节点ID
         * @return 节点
         */
        T getNode(PK id);
    }
}
