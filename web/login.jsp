
<!DOCTYPE html>
<html>


    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />

        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
        <script type="text/javascript">
            function submitMethod()
            {
                $('#loginform').submit();
            }

        </script>
    </head>
    <BODY>
        <div id="header-wrapper">
            <header id="header">
                <div class="5grid-layout">
                    <div class="row">
                        <div class="12u" id="logo"> <!-- Logo -->
                            <h1><a href="#" class="mobileUI-site-name">Parking System</a></h1>
                            <p>Control Area</p>
                        </div>
                    </div>
                </div>
                <div id="menu-wrapper">
                    <div class="5grid-layout">
                        <div class="row">
                            <div class="12u" id="menu">
                                <nav class="mobileUI-site-nav">
                                    <ul>
                                        <li class="current_page_item"><a href="login.jsp">Login</a></li>


                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </div>
        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <div class="row">
                                <div class="12u">
                                    <div id="banner"><a href="#"></a>
                                        <form action="LoginServlet" method="POST" id="loginform">

                                            <table>

                                                <th colspan="2">
                                                    <c:out value="${error.getErrorBody()}"/>
                                                </th>
                                                <tr>

                                                    <td>Email:</td>
                                                    <td><input type="text" name="email"  required ></td>

                                                </tr>

                                                <tr>

                                                    <td>Password:</td>
                                                    <td><input type="password" name="pass" required /></td>

                                                </tr>
                                                <tr>
                                                    <td><input type="button" value="Sign in" id="myButton4" onclick="submitMethod()"/></td>
                                                    <td><input type="reset" value="Cancel" id="myButton3"/></td>
                                                </tr>

                                            </table><br>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <script src ="js/cookieValidator.js"></script>
                            <script type="text/javascript">



                            </script>  
                        </div>


                    </div>
                </div>
            </div>

        </div>

        <div id="copyright" class="5grid-layout">
            <section>
            </section>
        </div>
    </body>
</html>

