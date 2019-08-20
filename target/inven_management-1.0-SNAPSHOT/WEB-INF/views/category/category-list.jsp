<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="right_col" role="main">
    <div class="">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>List Category</h2>

                    <div class="clearfix"></div>
                </div>

                <div class="x_content">

                    <a href="<c:url value="/category/add" />" class="btn btn-app"><li class="fa fa-plus"></li>Add</a>

                    <div class="container">
                        <form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/category/list/1" method="POST">
                            <div class="form-group">
                                <div class="form-group">
                                    <label for="id" class="control-label col-md-3 col-sm-3 col-xs-12">ID</label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <form:input path="id" cssClass="form-control col-md-7 col-xs-12" />
                                    </div>
                                </div>
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="code">Code <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <form:input path="code" cssClass="form-control col-md-7 col-xs-12" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Name <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <form:input path="name" cssClass="form-control col-md-7 col-xs-12"/>
                                </div>
                            </div>
                            <div class="ln_solid"></div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-success">Search</button>
                            </div>

                        </form:form>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-striped jambo_table bulk_action">
                            <thead>
                            <tr class="headings">
                                <th class="column-title">#</th>
                                <th class="column-title">ID</th>
                                <th class="column-title">Code</th>
                                <th class="column-title">Name</th>
                                <th class="column-title">Description</th>
                                <th class="column-title no-link last"><span class="nobr">Action</span>
                                </th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${categories}" var="category" varStatus="loop">
                                <c:choose>
                                    <c:when test="${loop.index%2==0}">
                                        <tr class="even pointer">
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="odd pointer">
                                    </c:otherwise>
                                </c:choose>
                                    <td class=" ">${pageInfo.offset + loop.index + 1}</td>
                                    <td class=" ">${category.id}</td>
                                    <td class=" ">${category.code}</td>
                                    <td class=" ">${category.name}</td>
                                    <td class=" ">${category.description}</td>
                                    <td>
                                        <a href="<c:url value="/category/view/${category.id}"/>" class="btn btn-round btn-default">View</a>
                                        <a href="<c:url value="/category/edit/${category.id}" />" class="btn btn-round btn-primary">Edit</a>
                                        <a onclick="confirmDelete(${category.id});" class="btn btn-round btn-danger">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <jsp:include page="../layout/paging.jsp" />
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script type="text/javascript">
    function  confirmDelete(id) {
        if(confirm('Do you want delete this category?')) {
            window.location.href='<c:url value="/category/delete/"/> ' + id;
        }
        
    }

    function gotoPage(page) {
        $('#searchForm').attr('action', '<c:url value="/category/list/" /> '+page);
        $('#searchForm').submit();
    }
    $(document).ready(function(){
        processMessage();
    });
    function processMessage(){
        var msgSuccess = '${msgSuccess}';
        var msgError = '${msgError}';
        if(msgSuccess){
            new PNotify({
                title: ' Success',
                text: msgSuccess,
                type: 'success',
                styling: 'bootstrap3'
            });
        }
        if(msgError){
            new PNotify({
                title: ' Error',
                text: msgError,
                type: 'error',
                styling: 'bootstrap3'
            });
        }
    }

</script>