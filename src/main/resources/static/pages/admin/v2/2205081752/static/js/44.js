webpackJsonp([44],{lK93:function(t,e,l){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=l("0xDb"),s={data:function(){return{visible:!1,actionUrl:"/act/procdef/deploy",limit:1,multiple:!1,fileList:[]}},methods:{init:function(){this.visible=!0,this.actionUrl=Object(i.a)()+"/act/procdef/deploy",this.fileList=[]},changeHandle:function(t,e){},exceedHandle:function(t,e){this.$message.warning("当前限制选择 "+this.limit+" 个文件，本次选择了 "+t.length+" 个文件，共选择了 "+(t.length+e.length)+" 个文件")}}},a={render:function(){var t=this,e=t.$createElement,l=t._self._c||e;return l("el-dialog",{staticClass:"mod-procdef-deploy",attrs:{title:"部署流程资源","close-on-click-modal":!1,visible:t.visible},on:{"update:visible":function(e){t.visible=e}}},[l("el-form",{attrs:{"label-width":"120px"}},[l("el-row",{attrs:{gutter:20}},[l("el-col",{attrs:{span:11}},[l("el-form-item",{attrs:{label:"流程资源上传"}},[l("el-upload",{staticClass:"upload-demo",attrs:{action:t.actionUrl,"on-change":t.changeHandle,limit:t.limit,"on-exceed":t.exceedHandle,"file-list":t.fileList}},[l("el-button",{attrs:{size:"small",type:"primary"}},[t._v("点击上传")])],1)],1)],1)],1)],1),t._v(" "),l("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[l("el-button",{on:{click:function(e){t.visible=!1,t.$emit("refreshDataList")}}},[t._v("取消")])],1)],1)},staticRenderFns:[]},n=l("VU/8")(s,a,!1,null,null,null);e.default=n.exports}});