<%-- 
    Document   : register
    Created on : 19-Feb-2020, 07:19:23
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <script type="text/javascript">
        function checkAccept(x, y)
        {
            if (x.checked)
                y.disabled = false;
            else
                y.disabled = true;
        }
        function comparePassword(x, y)
        {
            if (x === y)
            {
                return true;
            } else
            {
                alert("Password and Re-entered Passoword didn't matched");
                return false;
            }
        }
        function checkdate(x)
        {
            var today = new Date();
            var input = new Date(x);
            var age = today.getFullYear() - input.getFullYear();
            if (age < 18)
            {
                alert("Input age is less than 18");
                return false;
            } else
            {
                return true;
            }

        }
        function validateRegistrationForm(form) {
            let result = checkdate(form.dob.value);
            result = result && comparePassword(form.password.value, form.rpassword.value);
            return result;
        }
    </script>
    <body>
        <div class="container">
            <div class="row">
                <div class="col col-md-10">


                    <form action="UserData?op=add" method="post" class="form" name="regForm" onsubmit="return validateRegistrationForm(regForm);"> 
                        <center>
                            <h2 class="bg-light">Registration page </h2>
                            <table class="table bg-light"> 
                                <tr>
                                    <td>Enter your Name </td>
                                    <td><input type="text" name="name" required="required" autocomplete="off"/> </td>
                                </tr> <tr>
                                    <td>Enter your Father's Name </td>
                                    <td><input type="text" name="fname" required="required"/> </td>
                                </tr>
                                <tr>
                                    <td>Enter your DOB </td>
                                    <td><input type="date" name="dob" class="form-control"  /> </td>
                                </tr>
                                <tr>
                                    <td>Enter your User_id</td>
                                    <td><input type="email" required="required" name="user_id" class="form-control"/></td>
                                </tr>
                                <tr>
                                    <td>Enter your Password</td>
                                    <td><input type="password" name="password" class="form-control" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"/></br>
                                    <span><b>Password should be minimum 8 char and contains one Upper, one Lowe, one Special and one digit</b></span></td>
                                </tr>
                                <tr>
                                    <td>Retype Password</td>
                                    <td><input type="password" name="rpassword" class="form-control"/></td>
                                </tr>
                                <tr>
                                    <td>Select Your Gender </td>
                                    <td> <input type="radio" name="gender" value="Male"/> Male<br/>
                                        <input type="radio" name="gender" value="Female"/>Female<br/>
                                    </td>
                                </tr> 
                                <td>Select Your Hobbies </td>
                                <td>
                                    <input type="checkbox" name="hobbies" value="Dancing"/>Dancing
                                    <input type="checkbox" name="hobbies" value="Singing"/>Singing <br/>
                                    <input type="checkbox" name="hobbies" value="Cooking"/>Cooking
                                    <input type="checkbox" name="hobbies" value="Drawing"/>Drawing
                                </td>
                                </tr>
                                <tr>
                                    <th colspan="2">
                                        <input type="checkbox" id="accept" value="accept" onchange="checkAccept(this, submit);">Accept Terms and Conditions
                                    </th>
                                </tr>
                                <tr>

                                    <th colspan="2">
                                        <input type="reset" value="Clear" class="form-control btn btn-dark" />
                                        <input type="submit" value="submit" class="form-control btn btn-primary" disabled="disabled" id="submit"/> 
                                    </th>
                                </tr>

                            </table>
                        </center>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>