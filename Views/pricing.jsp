<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="declarations.jsp"%>
  
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>KookieTalk Pricing Structure</title>

    <!-- 
    <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="pricing.css" rel="stylesheet">
    -->
  </head>

  <body>

    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
      <h5 class="my-0 mr-md-auto font-weight-normal"><img src="<spring:url value='/resource/images/image02.png' />"
				class="rounded float-left" alt="Kookietalk.com" height="40px" /></h5>
      <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="#">Features</a>
        <a class="p-2 text-dark" href="#">Support</a>
        <a class="p-2 text-dark" href="#">Pricing</a>
      </nav>
      <a class="btn btn-outline-primary" href="user/newStudent">Sign up</a>
    </div>

    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      <h1 class="display-4">Pricing</h1>
      <p class="lead">KookieTalk is dedicated to student comprehension of American dialect, grammer, and vocabulary.  Whether attending "free talk" sessions or our structured curriculum classes, our focus is to prepare students for real life situations.</p>
    </div>

    <div class="container">
      <div class="card-deck mb-3 text-center">
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Sugar</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">$50 <small class="text-muted">/ session</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li>Single session commitment</li>
              <li>Developed curriculum or free talk</li>
              <li>Email support</li>
              <li>Help center access</li>
            </ul>
          </div>
        </div>
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Peanut Butter</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">$45 <small class="text-muted">/ session</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li>Five session commitment</li>
              <li>Developed curriculum or free talk</li>
              <li>Priority email support</li>
              <li>Help center access</li>
            </ul>
          </div>
        </div>
        <div class="card mb-4 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Chocolate Chip with Pecans</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title pricing-card-title">$39 <small class="text-muted">/ session</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li>Ten session commitment</li>
              <li>Developed curriculum or free talk</li>
              <li>Phone and email support</li>
              <li>Help center access</li>
            </ul>
          </div>
        </div>
      </div>
      <%@ include file="footer.jsp"%>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- 
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../../../assets/js/vendor/popper.min.js"></script>
    <script src="../../../../dist/js/bootstrap.min.js"></script>
    <script src="../../../../assets/js/vendor/holder.min.js"></script>
    <script>
      Holder.addTheme('thumb', {
        bg: '#55595c',
        fg: '#eceeef',
        text: 'Thumbnail'
      });
    </script>
     -->
  </body>
</html>
