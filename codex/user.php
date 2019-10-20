<?php
session_start();
?>

<!DOCTYPE html>

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
</head>

<script>
    $(document).ready(function() {
        var SGD = 0.73;
        var JPY = 0.0092;
        var INR = 0.014;

        var i = 0;
        var txt = "Hey " + "<?php echo $_SESSION["username"]; ?>" + "!";
        var speed = 500;

        typeWriter();

        function typeWriter() {
            if (i < txt.length) {
                document.getElementById("name").innerHTML += txt.charAt(i);
                i++;
                setTimeout(typeWriter, speed);
            }
        }

        $("#allbranchesdata").on("click", function() {
            var currency_code = document.getElementById("currency_code").value;
            var table = $("#balancedata table tbody");
            var total_amount = 0;
            table.find('tr').each(function(i, el) {
                var $tds = $(this).find('td'),
                    amount = $tds.eq(2).text(),
                    currency = $tds.eq(3).text();
                if (currency == "SGD") {
                    amount = amount * SGD;
                } else if (currency == "JPY") {
                    amount = amount * JPY;
                } else if (currency == "INR") {
                    amount = amount * JPY;
                }
                var rowid = "rowid" + i;
                document.getElementById(rowid).innerHTML = amount;
                total_amount = total_amount + amount;
            });

            document.getElementById("rowid4").innerHTML = total_amount;

        });

        var urlAccountList = "http://10.93.27.18:8888/getXERates";
        $.ajax({
            type: "GET",
            url: urlAccountList,
            success: function(data) {
                document.getElementById("customerdata").innerHTML = data;
            }
        });

        document.getElementById("customerdata").innerHTML = htmldata;

    });
</script>

<style>
    .login-container {
        margin-top: 5%;
        margin-bottom: 5%;
    }

    .login-form-1 {
        padding: 5%;
        box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0 rgba(0, 0, 0, 0.19);
    }

    .login-form-1 h3 {
        text-align: center;
        color: #333;
    }

    .login-form-2 {
        padding: 5%;
        background: #003e75;
        box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0 rgba(0, 0, 0, 0.19);
    }

    .login-form-2 h3 {
        text-align: center;
        color: #fff;
    }

    .login-container form {
        padding: 10%;
    }

    .btnSubmit {
        width: 50%;
        border-radius: 1rem;
        padding: 1.5%;
        border: none;
        cursor: pointer;
    }

    .login-form-1 .btnSubmit {
        font-weight: 600;
        color: #fff;
        background-color: #0062cc;
    }

    .login-form-2 .btnSubmit {
        font-weight: 600;
        color: #0062cc;
        background-color: #fff;
    }

    .login-form-2 .ForgetPwd {
        color: #fff;
        font-weight: 600;
        text-decoration: none;
    }

    .login-form-1 .ForgetPwd {
        color: #0062cc;
        font-weight: 600;
        text-decoration: none;
    }
</style>

<body>
    <div class="container login-container">
        <div class="row login-form-1">
            <h1 id="name"></h1>
            <p class="page-description">Here is the list of your holdings in multiple banks across branches</p>
            <h3 class="no-browser-support">Sorry, Your Browser Doesn't Support the Web Speech API. Try Opening This Demo In Google Chrome.</h3>
            <hr>
            <div id="balancedata" class="container">
                <table class="table" id="customer">
                    <thead>
                        <tr>
                            <th>Branch</th>
                            <th>Country</th>
                            <th>Balance</th>
                            <th>Currency</th>
                            <th>Converted Balance</th>
                            <th>
                                <div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate="Enter group id">
                                    <input placeholder="Currency Code" class="input100" type="text" name="currency_code" id="currency_code">
                                </div>
                                <button id="allbranchesdata" class="login100-form-btn">
                                    Convert Amount
                                </button>
                            </th>
                        </tr>
                    </thead>
                    <tbody id="customerdata">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="container login-container">
        <div class="row">
            <div class="col-md-6 login-form-1">
                <h3>Meet Temonsity! Your Online Voice IVR</h3>
                <div class="input-single">
                    <textarea id="note-textarea" placeholder="Create a new query by typing or using voice recognition." rows="6"></textarea>
                </div>
                <button id="start-record-btn" title="Start Recording">Start Recognition</button>
                <button id="pause-record-btn" title="Pause Recording">Pause Recognition</button>
                <hr>
                <button id="save-note-btn" title="Save Note">Send And Save Query</button>
                <p id="recording-instructions">Press the <strong>Start Recognition</strong> button and allow access.</p>
                <ul id="notes">
                    <li>
                        <p class="no-notes">You don't have any requests yet.</p>
                    </li>
                </ul>
            </div>
            <div class="col-md-6 login-form-2">
                <h3>Meet Temonsity! Your Online Chat IVR</h3>
                <div class="input-single">
                    <textarea id="note-answer" placeholder="Answer" rows="2"></textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="container login-container">
        <h3>Your Previous Request! Only Stored in Cookies! We dont save any of your personal data in Online IVR</h3>

    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="script.js"></script>

</body>

</html>