<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Codex</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="images/icons/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>

<script>
    $(document).ready(function() {

        $("#accounts").hide();
        $("#group").hide();

        $("#account_name").on("keyup", function() {
            var value = $(this).val().toUpperCase();
            if (value.length > 3) {
                var urlAccountList = "http://10.93.27.34:8888/AccountList?clientName=" + value;
                alert(urlAccountList);
                $.ajax({
                    type: "GET",
                    url: urlAccountList,
                    success: function(result) {
                        var htmlData = "";
                        var i;
                        for (i = 0; i < result.length; i++) {
                            var accountNumber = result[i].accountNumber;
                            var customerName = result[i].customerName;
                            var branchCode = result[i].branchCode;
                            var country = result[i].country;
                            var idvalue = "rowid" + i;
                            htmlData = htmlData + "<tr id='" + idvalue + "'>";
                            htmlData = htmlData + "<td>" + accountNumber + "</td>";
                            htmlData = htmlData + "<td>" + customerName + "</td>";
                            htmlData = htmlData + "<td>" + branchCode + "</td>";
                            htmlData = htmlData + "<td>" + country + "</td>";
                            htmlData = htmlData + "<td><button onclick='deletedata(this)' id='" + idvalue + "'>x</button></td>";
                            htmlData = htmlData + "</tr>";
                        }
                        document.getElementById("accountsdata").innerHTML = htmlData;
                        $("#accounts").show();
                        alert("Success");
                    },
                    error: function(e) {
                        alert("Error");
                    },
                    done: function(e) {
                        alert("Done");
                    }
                });
            }
            //$("#myTable tr").filter(function() {
            //$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            //});
        });

        $("#register").on("click", function() {
            event.preventDefault();

            var user_name = document.getElementById("user_name");
            var pass_word = document.getElementById("pass_word");
            var category = document.getElementById("category");

            $.ajax({
                type: "POST",
                url: "register.php",
                data: {
                    user_name: user_name,
                    pass_word: pass_word,
                    category: category
                },
                success: function(data) {
                    alert("Registered successfully");
                }
            });
        });

        $("#create_group").on("click", function() {
            var result;
            var table = $("#accountvalues table tbody");
            table.find('tr').each(function(i, el) {
                var $tds = $(this).find('td'),
                    accountNumber = $tds.eq(0).text(),
                    customerName = $tds.eq(1).text(),
                    branchCode = $tds.eq(2).text(),
                    country = $tds.eq(3).text();
                if (result == undefined) {
                    result = accountNumber + "-" + customerName + "-" + branchCode + "-" + country;
                } else {
                    result = result + "--" + accountNumber + "-" + customerName + "-" + branchCode + "-" + country;
                }
            });

            var urlAccountList = "http://10.93.27.34:8888/saveGroup?accountDetails=" + result;

            alert(urlAccountList);

            $.ajax({
                type: "GET",
                url: urlAccountList,
                success: function(data) {
                    alert("Saved Successfully");
                }
            });

        });

        $("#search_group_details").on("click", function() {
            var group_id = document.getElementById("groupid").value;
            var urlAccountList = "http://10.93.27.34:8888/userAccountInGroup?groupId=" + group_id;
            alert(urlAccountList);
            $.ajax({
                type: "GET",
                url: urlAccountList,
                success: function(result) {
                    var htmlData = "";
                    var i;
                    for (i = 0; i < result.length; i++) {
                        var accountNumber = result[i].accountNumber;
                        var customerName = result[i].customerName;
                        var branchCode = result[i].branchCode;
                        var country = result[i].country;
                        htmlData = htmlData + "<td>" + accountNumber + "</td>";
                        htmlData = htmlData + "<td>" + customerName + "</td>";
                        htmlData = htmlData + "<td>" + branchCode + "</td>";
                        htmlData = htmlData + "<td>" + country + "</td>";
                        htmlData = htmlData + "</tr>";
                    }
                    document.getElementById("groupdata").innerHTML = htmlData;
                    $("#group").show();
                    alert("Success");
                },
                error: function(e) {
                    alert("Error");
                },
                done: function(e) {
                    alert("Done");
                }
            });
        });
    });


    function deletedata(x) {
        var rowid = x.id;
        document.getElementById(rowid).remove();
    }
</script>

<body>

    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100 p-b-20">
                <form class="login100-form validate-form">
                    <span class="login100-form-title p-b-70">
                        Welcome <?php echo $_SESSION["username"]; ?>
                    </span>
                    <div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate="Enter group id">
                        <input placeholder="Account Name" class="input100" type="text" id="account_name" name="account_name">
                    </div>
                    <button class="login100-form-btn">
                        Add Accounts to the group
                    </button>

                    <hr>

                    <div id="accountvalues" class="container">
                        <table class="table" id="accounts">
                            <thead>
                                <tr>
                                    <th>Account Number</th>
                                    <th>Customer Name</th>
                                    <th>Branch Code</th>
                                    <th>Country</th>
                                </tr>
                            </thead>
                            <tbody id="accountsdata">
                            </tbody>
                        </table>

                    </div>

                    <div class="container-login100-form-btn">
                        <button id="create_group" class="login100-form-btn">
                            Create Group
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100 p-b-20">
                <form class="login100-form validate-form">
                    <span class="login100-form-title p-b-70">
                        Search Group Details
                    </span>
                    <div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate="Enter group id">
                        <input placeholder="GroupId" class="input100" type="text" name="groupid" id="groupid">
                    </div>
                    <button id="search_group_details" class="login100-form-btn">
                        Search Group Details
                    </button>

                    <hr>

                    <div id="accountvalues" class="container">
                        <table class="table" id="group">
                            <thead>
                                <tr>
                                    <th>Account Number</th>
                                    <th>Customer Name</th>
                                    <th>Branch Code</th>
                                    <th>Country</th>
                                </tr>
                            </thead>
                            <tbody id="groupdata">
                            </tbody>
                        </table>

                    </div>
            </div>
            </form>
        </div>
    </div>
    </div>

    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100 p-b-20">
                <form method="post" action="admin.php" class="login100-form validate-form">
                    <span class="login100-form-title p-b-70">
                        User Registration
                    </span>
                    <div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate="Enter group id">
                        <input placeholder="Username" class="input100" type="text" name="user_name">
                    </div>
                    <div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate="Enter group id">
                        <input placeholder="Password" class="input100" type="text" name="pass_word">
                    </div>
                    <div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate="Enter group id">
                        <input placeholder="Category" class="input100" type="text" name="category">
                    </div>
                    <button id="register" class="login100-form-btn">
                        Register
                    </button>
                </form>
            </div>
        </div>
    </div>

    <div id="dropDownSelect1"></div>
    <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
    <script src="vendor/animsition/js/animsition.min.js"></script>
    <script src="vendor/bootstrap/js/popper.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/daterangepicker/moment.min.js"></script>
    <script src="vendor/daterangepicker/daterangepicker.js"></script>
    <script src="vendor/countdowntime/countdowntime.js"></script>
    <script src="js/main.js"></script>

</body>

</html>