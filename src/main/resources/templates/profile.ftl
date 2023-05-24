<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>User Profile</title>
</head>
<body>
<#include "parts/security.ftl">
<h1> ${name}</h1>
<h2>Roles </h2>
<#list user.getRoles() as role>
    ${role}
</#list>
<br> <br>
Phone  <#if user.getPhone()?has_content>${user.getPhone()} </#if> <br>
E-mail <#if user.getEmail()?has_content>${user.getEmail()} </#if> <br>
<h2> Booking history</h2>
    <#list bookings as booking>
        <a href="/housingById?housingId=${booking.getHousing().getId()}">${booking} at ${booking.getHousing().getCity()}</a>
    </#list>

<h2> Wishlist</h2>
    <#list wishlist as wish>
        ${wish} <form method="post" action="/wishDel">
    <input type="hidden" name="wishId" value=${wish.getId()}>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit">Delete</button>

    </form>
    </#list>
<#--</#if>-->
<br>
<a  href="/" class="btn btn-primary btn-lg">Home</a>

</body>
</html>