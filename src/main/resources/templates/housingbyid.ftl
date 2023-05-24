<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>RentMe</title>
</head>
<body>
<#include "parts/security.ftl">
<header class="header"> Header </header>
<div class="container">
    <div class="row">
        <div class="col">
            <h2> Rent Me</h2>
        </div>
        <div class="col">
            <h2> + 38 044 111 11 11 </h2>
        </div>
        <div class="col">
            <#if known>
                <a  href="/profile"> ${name} </a>
                <a  href="/logout" class="btn btn-primary btn-lg">Logout</a>
                <#else>
                <a href="/login" class="btn btn-primary btn-lg">Login</a>
                <a href="/registration" class="btn btn-primary btn-lg">New User</a>
            </#if>
        </div>
    </div>
</div>
<main class="main">Content</main>

<table class="table">
    <tbody>
        <tr>
            <td style="width: 350px;>
                <#list onehousing.getPhotos() as photo>
                    <img src="${photo.getLink()}" alt="Housing Photo" style="width: 300px;">
                    ${photo.getLink()}
                    <#if isAdmin>
                        <form method="post" action="/photoDel">
                            <input type="hidden" name="photoId" value=${photo.getId()}>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button type="submit">Delete</button>
                        </form>
                        <form method="post" action="/addPhoto">
                            <input type="hidden" name="housingId" value=${onehousing.getId()}>
                            <input type="url"  name="photoLink" required>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button type="submit">Add new photo</button>
                        </form>
                    </#if>
                </#list>
            </td>
            <td>
                <a  href="/" class="btn btn-primary btn-lg">Home</a>
                <br>
                ${onehousing.getDescription()}<br>
                <#if isAdmin>
                    <form method="post" action="/setDescription">
                        <input type="hidden" name="housingId" value=${onehousing.getId()}>
                        <input type="text"  name="description" required>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit">New description</button>
                    </form>
                </#if>
                City ${onehousing.getCity()}<br>
                <#if known>
                ${onehousing.getAddress()}<br>
                </#if>
                <table>
                    <thead>
                    <tr>
                        <th>
                            Rooms
                        </th>
                        <th>
                            Square, m2
                        </th>
                        <th>
                            Price, EUR
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            ${onehousing.getRooms()}
                        </td>
                        <td>
                            ${onehousing.getSquare()}
                        </td>
                        <td>
                            ${onehousing.getPrice()}
                            <#if isAdmin>
                                <form method="post" action="/setPrice">
                                    <input type="hidden" name="housingId" value=${onehousing.getId()}>
                                    <input type="number"  name="price" required>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <button type="submit">New price</button>
                                </form>
                            </#if>
                        </td>
                    </tr>

                    </tbody>
                </table>
                <#if known>
                    <br>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            Already booked dates
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <#list bookings as activebooking>
                            <li><a class="dropdown-item" > ${activebooking} <#if isAdmin>  ${activebooking.getConsumer().getUsername()}</#if></a></li>
                            </#list>
                        </ul>
                    </div>
                    <br>
                </#if>
                <#if isUser>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <form method="post" action="/book" class="form-inline">
                                <input type="hidden" name="housingId" value=${onehousing.getId()}>
                                <input type="date" name="from" class="form-control" placeholder="Checkin date">
                                <input type="date" name="until" class="form-control" placeholder="Checkout date">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" >
                                <button type="submit" class="btn btn-primary ml-2">Book</button>
                            </form>
                        </div>
                    </div>
                <form method="post" action="/addWish">
                    <input type="hidden" name="housingId" value=${onehousing.getId()}>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <button type="submit">Add to wishlist</button>
                </#if>
                <#if isAdmin>
                    <form method="post" action="/housingDel">
                        <input type="hidden" name="housingId" value=${onehousing.getId()}>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit">Delete</button>
                    </form>
                </#if>
            </td>
        </tr>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>