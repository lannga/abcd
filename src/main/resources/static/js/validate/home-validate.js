const setQueryValue = (dataQuery) => {
    (dataQuery.title != "") && $("input[name = 'title'").val(dataQuery.title);
    (dataQuery.jobName != "") && setValue($("select[name = 'jobName']"),dataQuery.jobName);
    (dataQuery.location != "") && setValue($("select[name = 'location']"), dataQuery.location);
    (dataQuery.type != "") && setValue($("select[name = 'type']"), dataQuery.type);
    (dataQuery.experience != "") && setValue($("select[name = 'experience']"), dataQuery.experience);
    (dataQuery.salary != "") && setValue($("select[name = 'salary']"), dataQuery.salary);
    (dataQuery.company != "") && $("input[name = 'company'").val(dataQuery.company);
}