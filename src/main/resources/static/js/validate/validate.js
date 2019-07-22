function warning(text) {
    if ($(this).parent().has("p").length < 1)
        $(this).parent().append("<p class ='alert alert-warning mt-3' style='color:red'>" + text + "</p>")
}

function validate() {
    $(this).siblings("p").remove();
}


function setValue(key,value) {
    if(value != null){
        key.children('option')
            .removeAttr('selected');
        key.children('option').each(function () {
            if($(this).val() == value)
            $(this).attr("selected",true)
        });
    }

}

function setValueText(key, value) {
    if (value != null) 
        key.val(value)
}

function setValueCheck(key,value){
    key.each(function(){
        for(let i = 0;i<value.length;i++)
            if($(this).val() == value[i]){
                $(this).prop("checked");
                $(this).attr("name","location");
            }
    })
}


