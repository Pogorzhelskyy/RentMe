<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="post" action="/registration" class="form-inline">
            <input type="text" name="username" class="form-control" placeholder="username">
            <#if errorUsername??>
            <div class="invalid-feedback" style="display: block; color: red;">
                ${errorUsername}
            </div>
            </#if>
            <input type="password" name="password" class="form-control" placeholder="password">
            <#if errorPassword??>
                <div class="invalid-feedback" style="display: block; color: red;">
                    ${errorPassword}
                </div>
            </#if>
            <input type="email" name="email" class="form-control" placeholder="email">
            <#if errorEmail??>
                <div class="invalid-feedback" style="display: block; color: red;">
                    ${errorEmail}
                </div>
            </#if>
            <input type="tel" name="phone" class="form-control" placeholder="phone">
            <input type="password" name="passwordConfirmation" class="form-control" placeholder="confirm password">
            <#if errorPasswordConfirmation??>
                <div class="invalid-feedback" style="display: block; color: red;">
                    ${errorPasswordConfirmation}
                </div>
            </#if>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary ml-2">Save</button>
        </form>
    </div>
</div>
<a  href="/" class="btn btn-primary btn-lg">Home</a>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>