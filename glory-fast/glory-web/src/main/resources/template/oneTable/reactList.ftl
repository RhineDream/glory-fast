import React, { PureComponent, Fragment } from 'react';
import { Row,Col,List, Card, Icon, Dropdown, Menu, Avatar, Tooltip, Table, Button, Form ,Select,message , InputNumber,Input, DatePicker,Popconfirm } from 'antd';
import numeral from 'numeral';
import StandardTable from '@/components/StandardTable';
import { connect } from 'dva';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import { formatWan } from '@/utils/utils';
import GlobalSearch from '@/components/GlobalSearch/GlobalSearch'
import moment from 'moment';
import ${(lowerClazzName)!""}manage from '@/models/${(lowerClazzName)!""}models';
import styles from './${(tableInfo.clazzName)!""}List.less';
import ${(tableInfo.clazzName)!""}Modal from './${(tableInfo.clazzName)!""}Modal'

const dateFormat = 'YYYY/MM/DD';
const formItemLayout = {
    labelCol: {
        xs: { span: 24 },
        sm: { span: 8 },
    },
    wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
    },
};
const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 16,
            offset: 8,
        },
    },
};

const FormItem = Form.Item;
const { Option } = Select;
const getValue = obj =>
  Object.keys(obj)
    .map(key => obj[key])
    .join(',');


@connect(({ ${(lowerClazzName)!""}manage, loading }) => ({
    ${(lowerClazzName)!""}list: ${(lowerClazzName)!""}manage.${(lowerClazzName)!""}list,
    loading: loading.effects['${(lowerClazzName)!""}manage/fetch${(tableInfo.clazzName)!""}List']
}))
@Form.create()

class ${(tableInfo.clazzName)!""}List extends PureComponent {
    state = {
        pagination: {
            pageSize: 10,
            total: 0,
            pageNo: 1,
        },
        selectedRows: [],
        add${(tableInfo.clazzName)!""}Visible: false,
        edit${(tableInfo.clazzName)!""}Visible: false,
        formValues: {},
        expandForm:false
    }
    componentDidMount() {
        const { dispatch } = this.props;
        dispatch({
            type: 'user/fetchCurrent',
        });
        const data = {
            pageSize: 10,
            pageNo: 1,
        };
        this.refreshTable(data)
    }

    refreshTable = (paginationdata) => {
        const { dispatch } = this.props;
        const data = { ...paginationdata } || { ...this.state.pagination }
        dispatch({
            type: '${(lowerClazzName)!""}manage/fetch${(tableInfo.clazzName)!""}List',
            payload: {
                ...data,
                callback: res => {
                    const pagination = { ...this.state.pagination };
                    pagination.total = res.total;
                    pagination.pageSize = res.pageSize;
                    this.setState({ pagination: pagination });
                },
            },
        });
    }

    toggleForm = () => {
        const { expandForm } = this.state;
        this.setState({
          expandForm: !expandForm,
        });
      };
 

    handleSelectRows = rows => {
        this.setState({
            selectedRows: rows,
        });
    };

    handleSearch = (values) => {
      this.refreshTable(values)
    };


    add${(tableInfo.clazzName)!""} = () => {
        this.setState({ add${(tableInfo.clazzName)!""}Visible: true })
    }
    cancelAdd${(tableInfo.clazzName)!""} = () => {
        this.setState({ add${(tableInfo.clazzName)!""}Visible: false })
        this.reset${(tableInfo.clazzName)!""}Form()
    }


    handleStandardTableChange = (pagination, filtersArg, sorter) => {
        const { dispatch } = this.props;
        const { formValues } = this.state;
        const data = {
            pageNo: pagination.current,
            pageSize: pagination.pageSize,
            ...formValues,
        };
        this.refreshTable(data)
    };

    handleSubmit = (e) => {
        const { dispatch } = this.props;
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            const data = { ...values }
            if (!err) {
                dispatch({
                    type: '${(lowerClazzName)!""}manage/fetchAdd${(tableInfo.clazzName)!""}',
                    payload: {
                        ...values,
                        callback: (res) => {
                            this.setState({ add${(tableInfo.clazzName)!""}Visible: false })
                            this.refreshTable()
                        }
                    }
                })
            }
        })
    }

    delete${(tableInfo.clazzName)!""} = (isMore,record) => {
        const { dispatch } = this.props;
        var data = null;
        if(isMore){
          const { selectedRows } = this.state;
          if (selectedRows.length === 0) {
              message.error("请选择一条信息")
              return;
          }
          data = selectedRows.map((item) => {
              return item.id
          })  
        }else{
          data = [record.id]
        }
     
        dispatch({
            type: '${(lowerClazzName)!""}manage/fetchDelete${(tableInfo.clazzName)!""}',
            payload: {
                data,
                callback: (res) => {
                    debugger
                    if (res.ok == true) {
                        message.success()
                        this.refreshTable()
                    }
                }
            }
        })
    }



    cancalEdit${(tableInfo.clazzName)!""} = () => {
        this.setState({ edit${(tableInfo.clazzName)!""}Visible: false })
        this.reset${(tableInfo.clazzName)!""}Form()
    }

    handleEdit = (e) => {
        const { dispatch, form } = this.props;
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            const data = { ...values, id: this.state.edit${(tableInfo.clazzName)!""}Id }
            if (!err) {
                dispatch({
                    type: '${(lowerClazzName)!""}manage/fetchEdit${(tableInfo.clazzName)!""}',
                    payload: {
                        ...data,
                        callback: (res) => {
                            this.setState({ edit${(tableInfo.clazzName)!""}Visible: false })
                            this.refreshTable()
                        }
                    }
                })
            }
        })
    }

    reset${(tableInfo.clazzName)!""}Form = () => {
        const { form } = this.props;
        form.resetFields()
    }

    edit${(tableInfo.clazzName)!""} = (record) => {
        console.log(record.lastLoginTime)
        const { form } = this.props;
        this.setState({edit${(tableInfo.clazzName)!""}Visible:true,edit${(tableInfo.clazzName)!""}Id:record.id})
        console.log(record.birthday&&record.birthday.replace(/-/g,'/'))
        form.setFieldsValue({
        <#list fieldLists as field>
            ${(field.javaFieldName)!""}: record.${(field.javaFieldName)!""},
        </#list>
          })
    }
 

    render() {
        const { loading, ${(lowerClazzName)!""}list, form } = this.props;
        const { getFieldDecorator } = form;
        const {
            selectedRows,
            pagination
        } = this.state;
        const columns = [
            {
                title: '序号',
                dataIndex: 'title',
                width: 50,
                render: (text, record, index) => `${r'${index + 1}'}`,
                fixed: 'left',
            },
        <#list fieldLists as field>
            {
                title: '${(field.fieldComment)!""}',
                dataIndex: '${(field.javaFieldName)!""}',
                width: 200,
            },
        </#list>
            {
                title: '创建时间',
                dataIndex: 'createTime',
                width: 200,
            },
            {
                title: '创建人',
                dataIndex: 'createBy_text',
                width: 100,
            },
            {
                title: '备注',
                dataIndex: 'remarks',
                width: 200,
            },
            {
                title: '操作',
                width: 100,
                fixed: 'right',
                render: (_, record) => {
                    return <Fragment>
                        <a onClick={() => { this.edit${(tableInfo.clazzName)!""}(record) }}>编辑</a>
                        <span className="ant-divider" />
                        <Popconfirm placement="top" title={'确认删除${(genCodeRecord.moduleDesc)!""}?'} onConfirm={()=> this.delete${(tableInfo.clazzName)!""}(false,record)} okText="确认" cancelText="取消">
                            <a  style={{color:'red'}}>删除</a>
                        </Popconfirm>
                    </Fragment>
                }
            }
        ]


        const searchColumns = [
          {label:'学生名称',dataIndex:'studentName'},
          {label:'姓名',dataIndex:'username'},
          {label:'电子邮件',dataIndex:'email'},
          {label:'电话',dataIndex:'phone'},
          {label:'性别',dataIndex:'sex',select:true,selectdata:[{value:'1',show:'男'},{value:'2',show:'女'}]},
        ]

        
        const add${(tableInfo.clazzName)!""}Modal = (
            <${(tableInfo.clazzName)!""}Modal
                visible={this.state.add${(tableInfo.clazzName)!""}Visible}
                cancleSubmit={this.cancelAdd${(tableInfo.clazzName)!""}}
                handleSubmit={this.handleSubmit}
                form={form}
                title={'新增${(genCodeRecord.moduleDesc)!""}信息'}
            />
        )

        const edit${(tableInfo.clazzName)!""}Modal = (
            <${(tableInfo.clazzName)!""}Modal
                visible={this.state.edit${(tableInfo.clazzName)!""}Visible}
                cancleSubmit={this.cancalEdit${(tableInfo.clazzName)!""}}
                handleSubmit={this.handleEdit}
                title={'修改${(genCodeRecord.moduleDesc)!""}信息'}
                form={form}
            />
        )

        const searchCon = (
          <GlobalSearch
            handleSearch = {this.handleSearch}
            toggleForm = {this.toggleForm}
            expandForm = {this.state.expandForm}
            searchColumns = {searchColumns}
          />
        )
        
        return (
            <PageHeaderWrapper title="${(genCodeRecord.moduleDesc)!""}管理">
                {add${(tableInfo.clazzName)!""}Modal}
                {edit${(tableInfo.clazzName)!""}Modal}
                <Card bordered={false}>
                    <div className={styles.tableList}>
                        <div className={styles.tableListForm}>
                          {searchCon}
                        </div>
                        <div className={styles.topbuttons}>
                            <Button icon="plus" type="primary" onClick={this.add${(tableInfo.clazzName)!""}}>
                                添加
                            </Button>
                            <Button icon="delete" type="danger" onClick={this.delete${(tableInfo.clazzName)!""}.bind(this,true)}>
                                批量删除
                            </Button>
                        </div>
                        <StandardTable
                          bordered
                          size="small"
                          scroll={{ x: 1500, y: 700 }}
                          selectedRows={selectedRows}
                          loading={loading}
                          pagination={pagination}
                          dataSource={${(lowerClazzName)!""}list && ${(lowerClazzName)!""}list.records}
                          columns={columns}
                          onSelectRow={this.handleSelectRows}
                          onChange={this.handleStandardTableChange}
                          rowKey="id"
                        />
                    </div>
                </Card>
            </PageHeaderWrapper>

        );
    }
}

export default ${(tableInfo.clazzName)!""}List;
