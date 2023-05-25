<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
<#--<div class="form-row">-->
<#--    <div class="form-group col-md-6">-->
<#--        <form method="post" action="/registration" class="form-inline">-->
<#--            <input type="text" name="username" class="form-control" placeholder="username">-->
<#--            <input type="password" name="password" class="form-control" placeholder="user password">-->
<#--            <input type="email" name="email" class="form-control" placeholder="email">-->
<#--            <input type="tel" name="phone" class="form-control" placeholder="phone">-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--            <button type="submit" class="btn btn-primary ml-2">Save</button>-->
<#--        </form>-->
<#--    </div>-->

<div class="container">
    <h1>Registration Form</h1>

    <form id="registrationForm">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" required>
            <div id="usernameError" class="invalid-feedback"></div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" required>
            <div id="passwordError" class="invalid-feedback"></div>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" required>
            <div id="emailError" class="invalid-feedback"></div>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="tel" class="form-control" id="phone">
            <div id="phoneError" class="invalid-feedback"></div>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>

    <a href="/index" class="btn btn-primary btn-lg">Cancel</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    const form = document.getElementById('registrationForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const usernameInput = document.getElementById('username');
        const passwordInput = document.getElementById('password');
        const emailInput = document.getElementById('email');
        const phoneInput = document.getElementById('phone');

        const usernameError = document.getElementById('usernameError');
        const passwordError = document.getElementById('passwordError');
        const emailError = document.getElementById('emailError');
        const phoneError = document.getElementById('phoneError');

        // Очищаем предыдущие сообщения об ошибках
        usernameError.textContent = '';
        passwordError.textContent = '';
        emailError.textContent = '';
        phoneError.textContent = '';

        // Получаем значения полей формы
        const username = usernameInput.value;
        const password = passwordInput.value;
        const email = emailInput.value;
        const phone = phoneInput.value;

        // Создаем объект с данными пользователя
        const user = {
            username: username,
            password: password,
            email: email,
            phone: phone
        };

        // Отправляем POST-запрос на контроллер
        fetch('/registration', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                if (data.errors) {
                    // Если есть ошибки валидации, отображаем их на форме
                    if (data.errors.username) {
                        usernameInput.classList.add('is-invalid');
                        usernameError.textContent = data.errors.username;
                    }
                    if (data.errors.password) {
                        passwordInput.classList.add('is-invalid');
                        passwordError.textContent = data.errors.password;
                    }
                    if (data.errors.email) {
                        emailInput.classList.add('is-invalid');
                        emailError.textContent = data.errors.email;
                    }
                } else if (data.usernameError) {
                    // Если ошибка "User exists!", отображаем ее на форме
                    usernameInput.classList.add('is-invalid');
                    usernameError.textContent = data.usernameError;
                } else {
                    // Если успешная регистрация, перенаправляем на страницу входа
                    window.location.href = '/login';
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
</script>
</body>
</html>