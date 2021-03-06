<!-- jQuery -->
<script src="vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="vendor/maskedinput/jquery.maskedinput.min.js"></script>

<script src="vendor/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="vendor/bootstrap-datepicker/js/bootstrap-datepicker.es.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="vendor/metisMenu/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="vendor/datatables-plugins/sorting/date-uk.js"></script>

<script src="js/bootbox.min.js"></script>        

<script src="vendor/handlebars/handlebars-v4.0.10.js"></script>

<script src="vendor/moment/moment-with-locales.min.js"></script>
<script src="vendor/multiselect/js/jquery.multi-select.js"></script>
<script src="vendor/jquery.quicksearch.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/sb-admin-2.js"></script>
<script src="js/globals.js"></script>
<script src="js/common.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        Handlebars.registerHelper('select2', function(value, options) {
            // Create a select element 
            var select = document.createElement('select');
            // Populate it with the option HTML
            select.innerHTML = options.fn(this);
            // Set the value
            select.value = value;
            // Find the selected node, if it exists, add the selected attribute to it
            if (select.children[select.selectedIndex])
                select.children[select.selectedIndex].setAttribute('selected', 'selected');
            return select.innerHTML;
        });
    });
</script>