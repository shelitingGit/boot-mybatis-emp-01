<%--
  Created by IntelliJ IDEA.
  User: sheli
  Date: 2020/2/7
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.po.*,com.service.*,com.serviceImpl.*,com.controller.*,java.util.*"%>
<html>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head>

    <title>管理系统</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
    <script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#win').window('close');  // close a window
            $("#btupdate").hide();
            //添加前数据准备
            $.getJSON("doinit_Emp.do",function(map){
                var lswf=map.lswf;
                var lsdep=map.lsdep;
                //生成复选框
                for(var i=0;i<lswf.length;i++){
                    var wf=lswf[i];
                    $("#wf").append("<input type='checkbox' name='wids' value='"+wf.wid+"'/>"+wf.wname);
                }
                //处理下拉列表
                $('#depid').combobox({
                    data:lsdep,
                    valueField:'depid',
                    textField:'depname',
                    value:1,
                    panelHeight:80
                });
            });

        });
        /*******员工列表***********/
        $(function(){
            $('#dg').datagrid({
                url:'findPageAll_Emp.do',
                singleSelect:true,
                striped:true,
                width:'890',
                pagination:true,
                pageList:[5,10,15,20],
                pageSize:5,
                columns : [ [ {
                    field : 'eid',
                    title : '编号',
                    width : 100,
                    align:'center'

                }, {
                    field : 'ename',
                    title : '姓名',
                    width : 100,
                    align:'center'
                }, {
                    field : 'sex',
                    title : '性别',
                    width : 100,
                    align:'center'
                }, {
                    field : 'address',
                    title : '地址',
                    width : 100,
                    align:'center'
                }, {
                    field : 'sdate',
                    title : '生日',
                    width : 100,
                    align:'center'
                },{
                    field : 'depname',
                    title : '部门',
                    width : 100,
                    align:'center'
                }, {
                    field : 'opt',
                    title : '操作',
                    width : 150,
                    align:'center',
                    formatter:function(value,row,index){
                        var bt1='<input type="button" value="删除" onclick="dodelById('+row.eid+')">';
                        var bt2='<input type="button" value="编辑" onclick="findById('+row.eid+')">';
                        var bt3='<input type="button" value="详细" onclick="findDetail('+row.eid+')">';
                        return bt1+'&nbsp;'+bt2+'&nbsp;'+bt3;
                    }
                } ] ]
            });
        });


        /*******员工列表end***********/
        /*******删除和查找***********/
        function dodelById(eid){
            $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                if (r){
                    $.get('delById_Emp.do?eid='+eid,function(code){
                        if(code=='1'){
                            $.messager.alert('提示','删除成功');
                            $('#dg').datagrid('reload');    // 重新载入当前页面数据
                        }else{
                            $.messager.alert('提示','删除失败');
                        }
                    });
                }
            });

        }
        function findById(eid){
            $.getJSON('findById_Emp.do?eid='+eid,function(emp){
                //删除原来复选框选中的值
                $(":checkbox[name='wids']").each(function(){
                    $(this).prop("checked",false);

                });
                //处理表单
                $('#ffemp').form('load',{
                    eid:emp.eid,
                    ename:emp.ename,
                    sex:emp.sex,
                    address:emp.address,
                    sdate:emp.sdate,
                    depid:emp.depid,
                    emoney:emp.emoney
                });

                //处理福利
                var wids=emp.wids;
                //设置选中
                $(":checkbox[name='wids']").each(function(){
                    for(var i=0;i<wids.length;i++){
                        if($(this).val()==wids[i]){
                            $(this).prop("checked",true);
                        }
                    }
                });
                $("#btupdate").show();
                $("#btsave").hide();
            });
        }
        function findDetail(eid){
            $.getJSON("findDetail_Emp.do?eid="+eid,function(emp){
                //给员工详细信息中文本设值
                $("#enametext").html(emp.ename);
                $("#sextext").html(emp.sex);
                $("#addresstext").html(emp.address);
                $("#sdatetext").html(emp.sdate);

                $("#depnametext").html(emp.depname);
                $("#emoneytext").html(emp.emoney);
                //获取员工福利
                var lswf=emp.lswf;
                var wnames=[];//获取福利名称数组
                for(var i=0;i<lswf.length;i++){
                    var wf=lswf[i];
                    wnames.push(wf.wname);
                }
                var strwname=wnames.join(',');
                $("#wftext").html(strwname);
                $("#dtmyphoto").attr("src",'uppic/'+emp.photo);
                $('#win').window('open');  // open a window
            });
        }
        /*******删除和查找end***********/
        /*******保存和修改***********/
        $(function(){
            /*******保存***********/
            $("#btsave").click(function(){
                $.messager.progress();	// 显示进度条
                $('#ffemp').form('submit', {
                    url:'save_Emp.do',
                    onSubmit: function(){
                        var isValid = $(this).form('validate');
                        if (!isValid){
                            $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                        }
                        return isValid;	// 返回false终止表单提交
                    },
                    success: function(code){
                        if(code=='1'){
                            $.messager.alert('提示','保存成功');
                            $('#ffemp').form('reset');
                            $('#dg').datagrid('reload');    // 重新载入当前页面数据
                        }else{
                            $.messager.alert('提示','保存失败');
                        }
                        $.messager.progress('close');	// 如果提交成功则隐藏进度条
                    }
                });
            });
            /*******保存end***********/
            /*******修改***********/
            $("#btupdate").click(function(){
                $.messager.progress();	// 显示进度条
                $('#ffemp').form('submit', {
                    url:'update_Emp.do',
                    onSubmit: function(){
                        var isValid = $(this).form('validate');
                        if (!isValid){
                            $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                        }
                        return isValid;	// 返回false终止表单提交
                    },
                    success: function(code){
                        if(code=='1'){
                            $.messager.alert('提示','修改成功');
                            $('#ffemp').form('reset');
                            $("#btsave").show();
                            $("#btupdate").hide();
                            $('#dg').datagrid('reload');    // 重新载入当前页面数据
                        }else{
                            $.messager.alert('提示','修改失败');
                        }
                        $.messager.progress('close');	// 如果提交成功则隐藏进度条
                    }
                });
            });
            /*******修改end***********/
            //取消
            $("#btreset").click(function(){
                $("#ffemp").form('reset');
            });
        });
        /*******保存和修改end***********/
    </script>
</head>
<body>
<p align="center">员工列表</p>
<hr/>
<div align="center">
    <table id="dg"></table>
</div>
<p></p>

<p>
<form method="post" enctype="multipart/form-data" name="ffemp" id="ffemp">
    <table width="600" height="600" border="1" align="center">
        <tr bgcolor="#FFFFCC" align="center">
            <td colspan="3" >员工管理</td>
        </tr>
        <tr align="center">
            <td>姓名</td>
            <td>
                <input type="text" name="ename" id="ename" class="easyui-validatebox" data-options="required:true,missingMessage:'姓名'">
                <input type="hidden" name="eid" id="eid">
            </td>

        </tr>
        <tr align="center">
            <td>性别</td>
            <td>
                <input type="radio" name="sex" id="sex" value="男" checked="checked">男
                <input type="radio" name="sex" id="sex1" value="女">女
            </td>
        </tr>
        <tr align="center">
            <td>住址</td>
            <td>
                <input type="text" name="address" id="address" class="easyui-validatebox" data-options="required:true,missingMessage:'住址'">
            </td>
        </tr>
        <tr align="center">
            <td>生日</td>
            <td>
                <input type="date" name="sdate" id="sdate" value="1990-01-01">
            </td>
        </tr>

        <tr align="center">
            <td>部门</td>
            <td>
                <input id="depid" name="depid">
            </td>
        </tr>
        <tr align="center">
            <td>薪资</td>
            <td>
                <input type="text" name="emoney" id="emoney" class="easyui-validatebox" data-options="required:true,missingMessage:'薪资'">
            </td>
        </tr>
        <tr align="center">
            <td >福利</td>
            <td colspan="2">
                <span id="wf"></span>
            </td>
        </tr>
        <tr bgcolor="#FFFFCC" align="center">
            <td colspan="3" >
                <input type="button" name="btsave" id="btsave" value="保存">
                <input type="button" name="btupdate" id="btupdate" value="修改">
                <input type="button" name="btreset" id="btreset" value="取消">
            </td>

        </tr>

    </table>
</form>
</p>
<!-- 提示窗口,由于显示详细信息 -->
<div id="win" class="easyui-window" title="员工详细信息" style="width:620px;height:650px"
     data-options="iconCls:'icon-save',modal:true">
    <table width="600" height="600" border="1" align="center">
        <tr bgcolor="#FFFFCC" align="center">
            <td colspan="3" >信息展示</td>
        </tr>
        <tr align="center">
            <td width="50">姓名</td>
            <td>
                <span id="enametext"></span>
            </td>

        </tr>
        <tr align="center">
            <td>性别</td>
            <td>
                <span id="sextext"></span>
            </td>
        </tr>
        <tr align="center">
            <td>住址</td>
            <td>
                <span id="addresstext"></span>
            </td>
        </tr>
        <tr align="center">
            <td>生日</td>
            <td>
                <span id="sdatetext"></span>
            </td>
        </tr>

        <tr align="center">
            <td>部门</td>
            <td>
                <span id="depnametext"></span>
            </td>
        </tr>
        <tr align="center">
            <td>薪资</td>
            <td>
                <span id="emoneytext"></span>
            </td>
        </tr>
        <tr align="center">
            <td >福利</td>
            <td colspan="2">
                <span id="wftext"></span>
            </td>
        </tr>
    </table>
</div>
</body>
</html>