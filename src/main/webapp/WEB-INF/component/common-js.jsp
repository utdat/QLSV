<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>




<script>
	function update_score(id){
		var midterm, endterm, link;
		
		midterm = document.getElementById(id+"_mid");
		endterm = document.getElementById(id+"_end");
		link = document.getElementById(id+"_a");

		if(midterm.value !== ''){
			link.href += "&midterm=" + midterm.value;
		}
		if(endterm.value !== ''){
			link.href += "&endterm=" + endterm.value;
		}
	}
	
	function w3_open() {
		  if (mySidebar.style.display === 'block') {
		    mySidebar.style.display = 'none';
		    overlayBg.style.display = "none";
		  } else {
		    mySidebar.style.display = 'block';
		    overlayBg.style.display = "block";
		  }
		}

	function w3_close() {
	  mySidebar.style.display = "none";
	  overlayBg.style.display = "none";
	}

	function myFunction() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("table");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[1];
			if (td) {
				txtValue = td.textContent || td.innerText;
				if (txtValue.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}
	
	function showPass() {
		  var x = document.getElementById("pass");
		  if (x.type === "password") {
		    x.type = "text";
		  } else {
		    x.type = "password";
		  }
		}
	
	(function() {
		  'use strict';
		  window.addEventListener('load', function() {
		    // Get the forms we want to add validation styles to
		    var forms = document.getElementsByClassName('needs-validation');
		    // Loop over them and prevent submission
		    var validation = Array.prototype.filter.call(forms, function(form) {
		      form.addEventListener('submit', function(event) {
		        if (form.checkValidity() === false) {
		          event.preventDefault();
		          event.stopPropagation();
		        }
		        form.classList.add('was-validated');
		      }, false);
		    });
		  }, false);
		})();
	
	function myAccFunc() {
		  var x = document.getElementById("adminsubject");
		  if (x.className.indexOf("w3-show") == -1) {
		    x.className += " w3-show";
		  } else {
		    x.className = x.className.replace(" w3-show", "");
		  }
		}

	document.getElementById("myBtn").click();
</script>