<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Codex</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/shoelace-css/1.0.0-beta16/shoelace.css">
    <link rel="stylesheet" href="css/styles.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Codex</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Open Sans', Arial, sans-serif;
            font-weight: 700;
        }

        .welcome-section {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background-color: #ffffff;
            overflow: hidden;
        }

        .welcome-section .content-wrap {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate3d(-50%, -50%, 0);
        }

        .welcome-section .content-wrap .fly-in-text {
            list-style: none;
        }

        .welcome-section .content-wrap .fly-in-text li {
            display: inline-block;
            margin-right: 30px;
            font-size: 5em;
            color: #003e75;
            opacity: 1;
            transition: all 2s ease;
        }

        .welcome-section .content-wrap .fly-in-text li:last-child {
            margin-right: 0;
        }

        .welcome-section .content-wrap .enter-button {
            display: block;
            text-align: center;
            font-size: 1em;
            text-decoration: none;
            text-transform: uppercase;
            color: #375ffb;
            opacity: 1;
            transition: all 1s ease 2s;
        }

        .welcome-section.content-hidden .content-wrap .fly-in-text li {
            opacity: 0;
        }

        .welcome-section.content-hidden .content-wrap .fly-in-text li:nth-child(1) {
            transform: translate3d(-100px, 0, 0);
        }

        .welcome-section.content-hidden .content-wrap .fly-in-text li:nth-child(2) {
            transform: translate3d(100px, 0, 0);
        }

        .welcome-section.content-hidden .content-wrap .fly-in-text li:nth-child(3) {
            transform: translate3d(-100px, 0, 0);
        }

        .welcome-section.content-hidden .content-wrap .fly-in-text li:nth-child(4) {
            transform: translate3d(100px, 0, 0);
        }

        .welcome-section.content-hidden .content-wrap .fly-in-text li:nth-child(5) {
            transform: translate3d(-100px, 0, 0);
        }

        .welcome-section.content-hidden .content-wrap .fly-in-text li:nth-child(6) {
            transform: translate3d(100px, 0, 0);
        }

        .welcome-section.content-hidden .content-wrap .enter-button {
            opacity: 0;
            transform: translate3d(0, -30px, 0);
        }

        @media (min-width: 800px) {
            .welcome-section .content-wrap .fly-in-text li {
                font-size: 5em;
            }

            .welcome-section .content-wrap .enter-button {
                font-size: 0.5em;
            }
        }
    </style>
</head>

<body>

    <div class="welcome-section content-hidden">
        <div class="content-wrap">
            <ul class="fly-in-text">
                <li>T</li>
                <li>E</li>
                <li>M</li>
                <li>E</li>
                <li>N</li>
                <li>O</li>
                <li>S</li>
            </ul>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(function() {

            var welcomeSection = $('.welcome-section'),
                enterButton = welcomeSection.find('.enter-button');

            setTimeout(function() {
                welcomeSection.removeClass('content-hidden');
            }, 500);

            enterButton.on('click', function(e) {
                e.preventDefault();
                welcomeSection.addClass('content-hidden').fadeOut();
            });


        })();
    </script>

    <script>
        window.setTimeout(function() {

            window.location.href = "login.php";

        }, 5000);
    </script>

</body>

</html>