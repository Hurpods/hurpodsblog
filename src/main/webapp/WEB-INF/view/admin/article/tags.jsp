<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/5/12
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="content">
    <span style="position: relative;display: block;left: 27px;width: fit-content;">标签总数：${tagList.size()}</span>
    <c:if test="${tagList.size()!=0}">
        <c:forEach items="${tagList}" var="tag">
            <div class="tag" id="tag-${tag.tagId}">
                <form id="tags" style="width: fit-content;display: inline;">
                    <input type="checkbox" name="tagIds" style="margin-right: 50px;" value="${tag.tagId}">
                </form>
                <img src="/img/tag/tag.png" style="vertical-align: middle;"/>#${tag.tagName}
                <div class="button" style="width: 100px;background: red;position: relative;float: right;"
                     onclick="deleteTag(${tag.tagId})">
                    <button class="bttn" type="button">删除</button>
                </div>
                <div class="button" style="width: 100px;position: relative;float: right;margin-right: 60px;"
                     onclick="updateTag(${tag.tagId},'${tag.tagName}')">
                    <button class="bttn" type="button">修改</button>
                </div>
            </div>
        </c:forEach>
        <div style="cursor:pointer;position: relative;float: left;margin: 15px;">
            <img src="/img/tag/add.png" alt="增加" style="width: 50px"/>
        </div>
        <div class="button" style="width: 100px;position: relative;float: right;margin: 15px;background: red" onclick="batchDelete()">
            <button class="bttn" type="button">全部删除</button>
        </div>
        <div class="button" style="width: 100px;position: relative;float: right;margin: 15px;background: lightseagreen"
             onclick="selectAll()">
            <button class="bttn" type="button">全选</button>
        </div>
        <div class="button" style="width: 100px;position: relative;float: right;margin: 15px;background: lightseagreen"
             onclick="reserve()">
            <button class="bttn" type="button">反选</button>
        </div>
    </c:if>
</rapid:override>
<rapid:override name="script">
    <script type="text/javascript">
        let tagIds = $("input[name='tagIds']");

        function deleteTag(tagId) {
            $.confirm({
                title: '警告',
                content: '此操作不可逆，确认删除该标签？',
                type: 'red',
                confirmButtonClass: 'btn-danger',
                cancelButtonClass: 'btn-info',
                buttons: {
                    ok: {
                        text: "确认",
                        action: function () {
                            $.ajax({
                                url: "/admin/article/deleteTag/"+tagId,
                                dataType: "JSON",
                                async: "false",
                                success: function (data) {
                                    $.alert(data.msg);
                                    if (data.status === "true") {
                                        $("#tag-" + tagId).remove();
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        text: "取消"
                    }
                }
            });
        }

        function updateTag(tagId, tagName) {
            $.confirm({
                title: '修改',
                content: '<div class="form-group">'
                    + '<label>请输入想要修改的标签名：</label>'
                    + '<input type="text" class="name form-control" required id="newTagName" value="#'
                    + tagName
                    + '"/>'
                    + '</div>'
                    + '<p class="text-danger" style="display:none"></p>',
                type:'blue',
                confirmButtonClass: 'btn-info',
                cancelButtonClass: 'btn-info',
                buttons: {
                    ok: {
                        text: "确认",
                        action: function () {
                            $.ajax({
                                url: "/admin/article/updateTag",
                                data: {
                                    tagId:tagId,
                                    newTagName:$("#newTagName").val();
                                },
                                dataType:"JSON",
                                async: "false",
                                success: function (data) {
                                    $.alert(data.msg);
                                    if(data.status==="true"){
                                        window.location.reload();
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        text: "取消"
                    }
                }
            })
        }

        function addTag() {
            $.confirm({
                title: '添加',
                content: '<div class="form-group">'
                    + '<label>请输入想要添加的标签名：</label>'
                    + '<input type="text" class="name form-control" required id="newTagName"/>'
                    + '</div>'
                    + '<p class="text-danger" style="display:none"></p>',
                confirmButtonClass: 'btn-info',
                cancelButtonClass: 'btn-info',
                type:'blue',
                buttons: {
                    ok: {
                        text: "确认",
                        action: function () {
                            $.ajax({
                                url: "/admin/article/createTag",
                                data: {
                                    tagName:$("#newTagName").val()
                                },
                                dataType:"JSON",
                                async: "false",
                                success: function (data) {
                                    $.alert(data.msg);
                                    if(data.status==="true"){
                                        window.location.reload();
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        text: "取消"
                    }
                }
            })
        }

        function selectAll() {
            let flag = true;
            tagIds.each(function () {
                flag = flag && $(this).prop("checked");
            });
            if (flag) {
                tagIds.prop("checked", false);
            } else {
                tagIds.prop("checked", true);
            }
        }

        function reserve() {
            tagIds.each(function () {
                $(this).prop("checked", !$(this).prop("checked"));
            });
        }

        function batchDelete() {
            $.confirm({
                title: '警告',
                content: '此操作不可逆，确认删除该标签？',
                type: 'red',
                confirmButtonClass: 'btn-danger',
                cancelButtonClass: 'btn-info',
                buttons: {
                    ok: {
                        text: "确认",
                        action: function () {
                            $.ajax({
                                url: "/admin/article/batchDeleteTags",
                                data: {
                                    tagIds: $("#tags").serialize()
                                },
                                dataType: "JSON",
                                async: "false",
                                success: function (data) {
                                    $.alert(data.msg);
                                    if (data.status === "true") {
                                        tagIds.each(function () {
                                            if ($(this).prop("checked")) {
                                                $(this).remove();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        text: "取消"
                    }
                }
            });
        }
    </script>
</rapid:override>
<%@ include file="../backstage.jsp" %>