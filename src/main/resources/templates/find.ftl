<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="parts/main.css">
    <title>RentMe</title>
</head>
<body>
<#import "parts/pager.ftl" as p>
<#include "parts/security.ftl">
<header class="header">
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
                <a href="/profile"> ${name} </a>
                <a href="/logout" class="btn btn-primary btn-lg">Logout</a>
            <#else>
                <a href="/login" class="btn btn-primary btn-lg">Login</a>
                <a href="/registration" class="btn btn-primary btn-lg">New User</a>
            </#if>
        </div>
    </div>
</div>
</header>
<main class="main">
<div class="container">
    <h1>Available Housing</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Photo</th>
            <th>Max persons</th>
            <th>Square</th>
            <th>Rooms</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
            <@p.pager url page />
        <#list page.content as housing>
            <tr>
                <td>
                    <a href="/housing-by-id?housingId=${housing.getId()}">
                    <#if housing.getPhotos()?has_content>
                  <img src="${housing.getPhotos()?first.getLink()}" alt="Housing Photo" style="width: 100px;">
                    <#else>
                  <img src="https://caspianpolicy.com/no-image.png" alt="Housing Photo" style="width: 100px;">
                    </#if> </a>
                </td>
                <td><a href="/housing-by-id?housingId=${housing.getId()}">${housing.getMaxPersons()}</a></td>
                <td><a href="/housing-by-id?housingId=${housing.getId()}">${housing.getSquare()}</a></td>
                <td><a href="/housing-by-id?housingId=${housing.getId()}">${housing.getRooms()}</a></td>
                <td><a href="/housing-by-id?housingId=${housing.getId()}">${housing.getPrice()} EUR</a></td>
            </tr>
        </#list>
        <@p.pager url page />
        </tbody>
    </table>
</div>

<a  href="/" class="btn btn-primary btn-lg">Back</a>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>