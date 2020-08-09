import { query${(tableInfo.clazzName)!""}List,add${(tableInfo.clazzName)!""},edit${(tableInfo.clazzName)!""},delete${(tableInfo.clazzName)!""} } from '@/services/${(tableInfo.clazzName)!""}Service';
import { getUserInfo } from '@/utils/authority';
export default {
  namespace: '${(lowerClazzName)!""}manage',

  state: {
  },

  effects: {
    /* ${(genCodeRecord.moduleDesc)!""}-列表查询 */
    *fetch${(tableInfo.clazzName)!""}List(_, { call, put }){
      const {payload} = _;
      const { callback } = payload;
      const response = yield call(query${(tableInfo.clazzName)!""}List,payload)
      if (response.status === 200) {
        if (callback) callback(response.result);
      }
      yield put({
        type: 'save${(tableInfo.clazzName)!""}List', 
        payload: response.result,
      });
    },
    /* ${(genCodeRecord.moduleDesc)!""}-新增 */
    *fetchAdd${(tableInfo.clazzName)!""}(_, { call, put }) {
      const { payload } = _;
      const {callback} = payload;
      const response = yield call(add${(tableInfo.clazzName)!""}, payload);
      if (response.status === 200) {
        if (callback) callback(response);
      }
    },
    /* ${(genCodeRecord.moduleDesc)!""}-编辑 */
    *fetchEdit${(tableInfo.clazzName)!""}(_, { call, put }) {
        const { payload } = _;
        const {callback} = payload;
        const response = yield call(edit${(tableInfo.clazzName)!""}, payload);
        if (response.status === 200) {
          if (callback) callback(response);
        }
      },
    /*${(genCodeRecord.moduleDesc)!""}-删除*/
    *fetchDelete${(tableInfo.clazzName)!""}(_, { call, put }) {
        const { payload } = _;
        const {callback} = payload;
        const response = yield call(delete${(tableInfo.clazzName)!""}, payload);
        if (response.status === 200) {
          if (callback) callback(response);
        }
      },
  },

  reducers: {
    save${(tableInfo.clazzName)!""}List(state, action) {
        console.log(action.payload)
      return {
        ...state,
        ${(lowerClazzName)!""}list: action.payload
      };
    },
  },
};
