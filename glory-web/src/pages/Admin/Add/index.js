import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { formatMessage, FormattedMessage } from 'umi-plugin-react/locale';
import {
  Form,
  Input,
  DatePicker,
  Select,
  Button,
  Card,
  InputNumber,
  Radio,
  Icon,
  Tooltip,
  message,
} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import styles from './index.less';
import { Link, router } from 'umi';

const FormItem = Form.Item;
const { Option } = Select;
const { RangePicker } = DatePicker;
const { TextArea } = Input;

@connect(({ admin, loading }) => ({
  admin,
  submitting: loading.effects['admin/edit'],
}))
@Form.create()
class BasicForms extends PureComponent {

  componentDidMount() {
    const {
      match: { params = {} },
      dispatch,
    } = this.props;

    dispatch({
      type: 'admin/edit',
      payload: params,
    });
  }

  handleSubmit = e => {
    const { dispatch, form } = this.props;
    e.preventDefault();
    form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        dispatch({
          type: 'admin/save',
          payload: values,
          callback: respose => {
            console.log(respose)
            if (respose.status === 200) {
              message.success(respose.msg);
              router.push("/admin")
            } else {
              message.error(respose.msg);
            }
          }
        });
      }
    });
  };

  checkPassword = (rule, value, callback) => {
    const { form } = this.props;
    const password = form.getFieldValue('password');
    if (value) {
      if (value !== password) {
        callback('两次输入的密码不一致！');
      } else {
        callback();
      }
    } else {
      callback('请再次输入新密码！');
    }
  };

  render() {
    const { submitting,admin:{values={}} } = this.props;
    console.log(values);
    const {
      form: { getFieldDecorator, getFieldValue },
    } = this.props;

    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 7 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 12 },
        md: { span: 10 },
      },
    };

    const submitFormLayout = {
      wrapperCol: {
        xs: { span: 24, offset: 0 },
        sm: { span: 10, offset: 7 },
      },
    };

    return (
      <PageHeaderWrapper>
        <Card bordered={false}>
          <Form onSubmit={this.handleSubmit} hideRequiredMark style={{ marginTop: 8 }}>
            {getFieldDecorator('id', {
              initialValue: values.id || '',
            })(<Input />)}
            <FormItem {...formItemLayout} label='用户名'>
              {getFieldDecorator('loginName', {
                initialValue: values.loginName || '',
                rules: [
                  {
                    required: true,
                    message: '必填'
                  },
                ],
              })(<Input placeholder='请输入用户名' />)}
            </FormItem>

            <FormItem {...formItemLayout} label='姓名'>
              {getFieldDecorator('username', {
                initialValue: values.username || '',
                rules: [
                  {
                    required: true,
                    message: '必填'
                  },
                ],
              })(<Input placeholder='请输入姓名' />)}
            </FormItem>

            <FormItem {...formItemLayout} label='密码'>
              {getFieldDecorator('password', {
                rules: [
                  {
                    required: true,
                    message: '必填'
                  },
                ],
              })(<Input placeholder='请输入密码' />)}
            </FormItem>

            <FormItem {...formItemLayout} label='确认密码'>
              {getFieldDecorator('rePassword', {
                rules: [
                  {
                    required: true,
                    message: '必填'
                  },
                  {
                    validator: this.checkPassword,
                  },
                ],
              })(<Input placeholder='请再次输入密码' />)}
            </FormItem>

            <FormItem {...formItemLayout} label='邮箱'>
              {getFieldDecorator('email', {
                initialValue: values.email || '',
                rules: [
                  {
                    required: true,
                    message: '必填'
                  },
                  {
                    type: 'email',
                    message: '邮箱格式不正确',
                  },
                ],
              })(<Input placeholder='请再次输入邮箱' />)}
            </FormItem>

            <FormItem {...formItemLayout} label='电话'>
              {getFieldDecorator('phone', {
                initialValue: values.phone || '',
                rules: [
                  {
                    required: true,
                    message: '必填'
                  },
                ],
              })(<Input placeholder='请再次输入电话' />)}
            </FormItem>

            <FormItem {...formItemLayout} label='备注'>
              {getFieldDecorator('remarks')(
                <TextArea
                  style={{ minHeight: 32 }}
                  placeholder='备注'
                  rows={4}
                />
              )}
            </FormItem>

            <FormItem {...submitFormLayout} style={{ marginTop: 32 }}>
              <Button type="primary" htmlType="submit" loading={submitting}>
                <FormattedMessage id="form.save" />
              </Button>
              <Link to='/admin'>
                <Button style={{ marginLeft: 8 }}>
                  返回
                </Button>
              </Link>
            </FormItem>
          </Form>
        </Card>
      </PageHeaderWrapper>
    );
  }
}

export default BasicForms;
