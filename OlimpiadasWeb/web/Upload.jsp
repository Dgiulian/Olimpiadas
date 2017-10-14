<script type="text/javascript">
    $(document).ready(function() {

        // The event listener for the file upload
        document.getElementById('txtFileUpload').addEventListener('change', upload, false);

        // Method that checks that the browser supports the HTML5 File API
        function browserSupportFileUpload() {
            var isCompatible = false;
            if (window.File && window.FileReader && window.FileList && window.Blob) {
                isCompatible = true;
            }
            return isCompatible;
        }

        // Method that reads and processes the selected file
        function upload(evt) {
            if (!browserSupportFileUpload()) {
                alert('The File APIs are not fully supported in this browser!');
            } else {
                var data = null;
                var file = evt.target.files[0];
                var reader = new FileReader();
                reader.readAsText(file);
                reader.onload = function(event) {
                    var csvData = event.target.result;
                    data = $.csv.toArrays(csvData);
                    if (data && data.length > 0) {
                        recorrer_csv(data);
                    } else {
                        alert('No data to import!');
                    }
                };
                reader.onerror = function() {
                    alert('Unable to read ' + file.fileName);
                };
            }
        }

        function recorrer_csv(data) {

            var html = "";

            for (var i = 1; i < data.length; i++) {
                d = data[i];
                var arr = d.toString().split(';');
                agregar_jugador_csv( arr[2], arr[1], arr[3], arr[4]);
            }

            $("#fileHelp").html("Se cargaron " + (data.length - 1) + " registros");



            return html;
        }
    });
</script>
