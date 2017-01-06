<head>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <style>
        body { background-color: #eee; font: helvetica; }
        #container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
        .green { font-weight: bold; color: green; }
        .message { margin-bottom: 10px; }
        label { width:70px; display:inline-block;}
        .hide { display: none; }
        .error { color: red; font-size: 0.8em; }
    </style>
</head>
<body>

<div id="container">



    <h2>Get By ID</h2>
    <form id="idForm">
        <div class="error hide" id="idError">Please enter a valid ID in range 0-3</div>
        <label for="paymentid">ID (0-3): </label><input name="id" id="paymentid" value="0" type="number" />
        <input type="submit" value="Get Payment By ID" /> <br /><br/>
        <div id="paymentIdResponse"> </div>
    </form>

    <hr/>

    <h2>Submit new Person</h2>
    <form id="newPersonForm">
        <label for="nameInput">Name: </label>
        <input type="text" name="name" id="nameInput" />
        <br/>

        <label for="ageInput">Age: </label>
        <input type="text" name="age" id="ageInput" />
        <br/>
        <input type="submit" value="Save Person" /><br/><br/>
        <div id="personFormResponse" class="green"> </div>
    </form>
</div>


<script type="text/javascript">

    $(document).ready(function() {

        // Request Person by ID AJAX
        $('#idForm').submit(function(e) {
            var paymentId = +$('#paymentid').val();
            if(!validatePersonId(personId))
                return false;
            $.get('${pageContext.request.contextPath}/payment/' + paymentId, function(payment) {
                $('#paymentIdResponse').text(payment.name + ', description ' + payment.description);
            });
            e.preventDefault(); // prevent actual form submit
        });

        // Save Person AJAX Form Submit
        $('#newPersonForm').submit(function(e) {
            // will pass the form data using the jQuery serialize function
            $.post('${pageContext.request.contextPath}/api/person', $(this).serialize(), function(response) {
                $('#personFormResponse').text(response);
            });

            e.preventDefault(); // prevent actual form submit and page reload
        });

    });

    function validatePersonId(personId) {
        console.log(personId);
        if(personId === undefined || personId < 0 || personId > 3) {
            $('#idError').show();
            return false;
        }
        else {
            $('#idError').hide();
            return true;
        }
    }


</script>

</body>