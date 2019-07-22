
const defaultErrors = 
["Tiêu đề chưa được nhập",
"Mô tả chưa được nhập",
"Tên công ty chưa được nhập",
"Yêu cầu chưa được nhập"];

function check(errors,post) {
    const errorsSize = (errors != null) ? errors.length : 0;    
    for (let i = 0; i < errorsSize; i++) {
        if (errors[i] == defaultErrors[0])
            warning.bind($('#title'))(defaultErrors[0]);
        if (errors[i] == defaultErrors[1])
            warning.bind($('#description'))(defaultErrors[1]);
        if (errors[i] == defaultErrors[2])
            warning.bind($('#company'))(defaultErrors[2]);
        if (errors[i] == defaultErrors[3])
            warning.bind($('#requirement'))(defaultErrors[3]);
    }
    if(post != null){
        setValueText($('#description'), post.description);
        setValue($('#type'), post.type);
        setValue($('#jobName'), post.jobName);
        setValueText($('#requirement'), post.requirement);
        setValue($('#salary'),post.salary);
        setValue($('#experience'), post.experience);
        setValueText($('#title'), post.title);
        setValueText($('#company',post.company));
        setValueCheck($("input[type='checkbox']"),post.locations)
    }
}

let error = true;

function checkEmpty(){
    let content = null;
    if ($(this).val().length < 1) {
        error = true;
        switch ($(this).attr('id')) {
            case "title":                
                content = defaultErrors[0] ;
                break;
            case "description":
                content = defaultErrors[1];
                break; 
            case "company":
                content = defaultErrors[2];
                break; 
            case "requirement":
                content = defaultErrors[3];
                break;             
            default:
                break;
        }
        return warning.bind(this)(content);
    }
    error = false;
    validate.bind(this)();
}

$('#title').change(checkEmpty.bind($('#title')));
$('#description').change(checkEmpty.bind($('#description')));
$('#company').change(checkEmpty.bind($('#company')));
$('#requirement').change(checkEmpty.bind($('#requirement')));

$("input[type='checkbox']").change(function (){
    
    

    if ($(this).prop('checked'))
        $(this).attr("name", "location")
    else {
        const size = $("input[name='location']").length;
        if (size <= 1 && $(this).attr("name") == "location") {
            $("input[name='location']").prop("checked", true);
            $("input[name='location']").attr("name", "location");
        }
        else{
            $(this).removeProp("checked");
            $(this).removeAttr("name");
        }
    }
})

function setValueCheck(key, value) {
    key.each(function () {
        for (let i = 0; i < value.length; i++)
            
            if (this.value == value[i]) {
                $(this).prop("checked",true);
                $(this).attr("name", "location");
            }
    })
}

// $('form').submit(function (e) {
//     checkEmpty.bind($('#title'));
//     checkEmpty.bind($('#description'));
//     if (error) e.preventDefault();
// })

