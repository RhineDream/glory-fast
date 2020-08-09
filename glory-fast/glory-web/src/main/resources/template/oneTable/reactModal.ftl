import React, { PureComponent } from 'react';
import {
  Menu,
  Drawer,
  Select,
  DatePicker,
  Tooltip,
  Cascader,
  AutoComplete,
  Checkbox,
  Upload,
  message,
  Input,
  Row,
  Col,
  Button,
  Form,
  Modal,
  Icon
} from 'antd';
import classNames from 'classnames';
import moment from 'moment'

const dateFormat = 'YYYY/MM/DD';
const monthFormat = 'YYYY/MM';

const formItemLayout = {
    labelCol: {
      xs: { span: 24 },
      sm: { span: 5 },
    },
    wrapperCol: {
      xs: { span: 24 },
      sm: { span: 19 },
    },
  };

export default class ${(tableInfo.clazzName)!""}Modal extends PureComponent {
  render() {
    const { form ,handleSubmit ,visible ,cancleSubmit ,title} = this.props;
    const { getFieldDecorator } = form; 
    return (
        <Modal
        title={title}
        visible={visible}
        onCancel={cancleSubmit}
        onOk={handleSubmit}
        width='600px'
      >
        <Form {...formItemLayout} onSubmit={handleSubmit}>
        <#list fieldLists as field>
            <Form.Item
                    label={
                <span>
                ${(field.fieldComment)!""}&nbsp;
                <Tooltip title="请输入${(field.fieldComment)!""}">
                    <Icon type="question-circle-o" />
                </Tooltip>
                </span>
            }
            >
            {getFieldDecorator('${(field.javaFieldName)!""}', {
            rules: [{ required: true, message: '请输入${(field.fieldComment)!""}!', whitespace: true }],
            })(<Input />)}
            </Form.Item>
        </#list>
        </Form>
      </Modal>
    );
  }
}
