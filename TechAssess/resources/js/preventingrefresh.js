 // slight update to account for browsers not supporting e.which
    function disableF5(e) { if ((e.which || e.keyCode) == 116) e.preventDefault(); };
    // To disable f5
        /* jQuery < 1.7 */
    $(document).bind("keydown", disableF5);
    /* OR jQuery >= 1.7 */
    $(document).on("keydown", disableF5);

    // To re-enable f5
        /* jQuery < 1.7 */
    $(document).unbind("keydown", disableF5);
    /* OR jQuery >= 1.7 */
    $(document).off("keydown", disableF5);
 $(function() {
        $(this).bind("contextmenu", function(e) {
            e.preventDefault();
        });
    }); 