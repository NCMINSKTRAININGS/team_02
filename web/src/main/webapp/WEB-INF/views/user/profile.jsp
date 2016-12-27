<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-paddingtop50">
    <div class="container">
        <table class="table table-hover" >
            <h2 class="header-panel">${user.username}</h2>
            <tr>
                <td>First name : ${user.firstName}</td>
            </tr>
            <tr>
                <td>Last name : ${user.lastName}</td>
            </tr>
            <tr>
                <td>Email : ${user.email}</td>
            </tr>
            <tr>
                <td>Username : ${user.username}</td>
            </tr>
        </table>
    </div>
</div>
