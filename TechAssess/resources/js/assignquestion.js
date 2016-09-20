function validateAssignQuestionForm() {
    var examId = document.forms["addQuestion"]["examId"].value;
    var fromQuestionId = document.forms["addQuestion"]["fromQuestionId"].value;
    var toQuestionId = document.forms["addQuestion"]["toQuestionId"].value;
    var numbers = /^[0-9]+$/;
    if (examId == null || examId == "" && fromQuestionId == null || fromQuestionId == "" && toQuestionId == null || toQuestionId == "") {
        swal({
            title: "Error",
            text: "Please fillout all the fields",
            type: "error"
        });
        return false;
    } else if (examId == null || examId == "") {
        swal({
            title: "Error",
            text: "ExamId Can't be blank..!!",
            type: "error"
        });
        return false;
    } else if (fromQuestionId == null || fromQuestionId == "") {
        swal({
            title: "Error",
            text: "FromQuestionId Can't be blank..!!",
            type: "error"
        });
        return false;
    } else if (toQuestionId == null || toQuestionId == "") {
        swal({
            title: "Error",
            text: "ToQuestionId Can't be blank..!!",
            type: "error"
        });
        return false;
    } else if ((!(examId.match(numbers)))) {
        swal({
            title: "Error",
            text: "ExamId must be a number..!!",
            type: "error"
        });
        return false
    } else if ((!(fromQuestionId.match(numbers)))) {
        swal({
            title: "Error",
            text: "FromQuestionId must be a number..!!",
            type: "error"
        });
        return false
    } else if ((!(toQuestionId.match(numbers)))) {
        swal({
            title: "Error",
            text: "ToQuestionId must be a number..!!",
            type: "error"
        });
        return false
    }
}

function scrolify(tblAsJQueryObject, height) {
    var oTbl = tblAsJQueryObject;

    // for very large tables you can remove the four lines below
    // and wrap the table with <div> in the mark-up and assign
    // height and overflow property  
    var oTblDiv = $("<div/>");
    oTblDiv.css('height', height);
    oTblDiv.css('overflow', 'scroll');
    oTbl.wrap(oTblDiv);

    // save original width
    oTbl.attr("data-item-original-width", oTbl.width());
    oTbl.find('thead tr td').each(function() {
        $(this).attr("data-item-original-width", $(this).width());
    });
    oTbl.find('tbody tr:eq(0) td').each(function() {
        $(this).attr("data-item-original-width", $(this).width());
    });


    // clone the original table
    var newTbl = oTbl.clone();

    // remove table header from original table
    oTbl.find('thead tr').remove();
    // remove table body from new table
    newTbl.find('tbody tr').remove();

    oTbl.parent().parent().prepend(newTbl);
    newTbl.wrap("<div/>");

    // replace ORIGINAL COLUMN width                
    newTbl.width(newTbl.attr('data-item-original-width'));
    newTbl.find('thead tr td').each(function() {
        $(this).width($(this).attr("data-item-original-width"));
    });
    oTbl.width(oTbl.attr('data-item-original-width'));
    oTbl.find('tbody tr:eq(0) td').each(function() {
        $(this).width($(this).attr("data-item-original-width"));
    });
}

$(document).ready(function() {
    scrolify($('#tblNeedsScrolling'), 260);
    scrolify($('#tbl1NeedsScrolling'), 260); // 160 is height
});