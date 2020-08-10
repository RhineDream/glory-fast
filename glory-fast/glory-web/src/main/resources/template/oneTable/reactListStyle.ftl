@import '~antd/lib/style/themes/default.less';
@import '~@/utils/utils.less';

.DictList{
  margin-bottom: 20px;
  background: yellow;
  height: 900px;
}
.tableList {
  .tableListOperator {
    margin-bottom: 16px;
    button {
      margin-right: 8px;
    }
  }
  .userphoto{
    width:30px;
    height:30px;
    border-radius:5px
    }
    .topbuttons{
      display: flex;
      justify-content: flex-end;
      margin-bottom: 16px;
      button{
      margin-right: 8px;
      }
    }
}


.tableListForm {
  :global {
    .ant-form-item {
      display: flex;
      margin-right: 0;
      margin-bottom: 24px;
      > .ant-form-item-label {
        width: auto;
        padding-right: 8px;
        line-height: 32px;
      }
      .ant-form-item-control {
        line-height: 32px;
      }
    }
    .ant-form-item-control-wrapper {
      flex: 1;
    }
  }
  .submitButtons {
    display: block;
    margin-bottom: 24px;
    white-space: nowrap;
  }
  
}


@media screen and (max-width: @screen-lg) {
  .tableListForm :global(.ant-form-item) {
    margin-right: 24px;
  }
}

@media screen and (max-width: @screen-md) {
  .tableListForm :global(.ant-form-item) {
    margin-right: 8px;
  }
}