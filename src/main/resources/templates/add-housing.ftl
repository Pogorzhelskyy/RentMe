<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>RentMe</title>
</head>
<body>
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
                    <a  href="/profile"> ${name} </a>
                    <a  href="/logout" class="btn btn-primary btn-lg">Logout</a>
                <#else>
                    <a href="/login" class="btn btn-primary btn-lg">Login</a>
                    <a href="/registration" class="btn btn-primary btn-lg">New User</a>
                </#if>
            </div>
        </div>
    </div>
</header>
<main class="main">
    <h3>Adding new housing </h3>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="post" action="/add-housing" class="form-inline" >
                <input type="text" name="city" class="form-control" placeholder="City" required>
                <input type="text" name="address" class="form-control" placeholder="Address" required>
                <input type="number" name="rooms" class="form-control" placeholder="Quantity of rooms" required>
                <input type="number" name="square" class="form-control" placeholder="Square of housing" required>
                <input type="number" name="price" class="form-control" placeholder="Price per day" required>
                <input type="text" name="description" class="form-control" placeholder="Description" required>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-primary ml-2">Add Housing</button>
            </form>
        </div>
    </div>
        <div> <a  href="/" class="btn btn-primary btn-lg">Home</a> </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>