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
<h3>Select City and dates </h3>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/find" class="form-inline" onsubmit="return validateDateForm()">
            <input type="text" name="city" class="form-control" placeholder="City">

            <input type="date" name="from" id="checkinDate" class="form-control" placeholder="Checkin date">
            <input type="date" name="until" id="checkoutDate" class="form-control" placeholder="Checkout date">
            <button type="submit" class="btn btn-primary ml-2">Find</button>
        </form>
    </div>
</div>
    <#if isAdmin>
        <div> <a  href="/addHousing" class="btn btn-primary btn-lg">Add new housing</a> </div>
    </#if>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    function validateDateForm() {
        var today = new Date().toISOString().split('T')[0];
        var checkinDate = document.getElementById('checkinDate').value;
        var checkoutDate = document.getElementById('checkoutDate').value;

        if(checkinDate == null || checkoutDate ==null){
            alert("Checkin and checkout date should not be blank");
            return false;
        }
        if (checkinDate < today) {
            alert("Checkin date should not be earlier than today");
            return false;
        }
        if (checkoutDate <= checkinDate) {
            alert("Checkout date should be after checkin date");
            return false;
        }
    }
</script>
</body>
</html>