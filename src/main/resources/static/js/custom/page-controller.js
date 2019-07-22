const pageController = (numberOfPage , page, path,dataQuery)=>{
    let query ="";
    if(dataQuery != null)
        query = "?title=" + dataQuery.title + "&jobName=" + dataQuery.jobName + "&experience" + dataQuery.experience + "&type=" + dataQuery.type+"&location="+ dataQuery.location +"&salary="+dataQuery.salary+"&company="+dataQuery.company
    

    let pages;
    let start = (page >= 5 && numberOfPage > page) ? (page - 4) : 0;
    if(numberOfPage <= 5 ){
        pages = numberOfPage;   
        start = 0;
    } 
    else{        
        start = (page < 5) ? 0 : page - 4 ;
        pages = ((numberOfPage - start)<5)?(numberOfPage-start):5;
    }   
    $(".page").text(null);
    let element;   

    for (let i = start ; i < pages + start + 2; i++) {
        if (i == start) 
            element = "<li class ='prev'><a href='/"+path+"/"+ (page-1) + query+"'><i class='fas fa-chevron-left'></i></a></li>"
        else if (i != start && i != pages + start + 1 && i != page)
            element = "<li><a href='/" + path + "/" + i + query +"'>" + i +"</a></li>"
        else if (i != start && i != pages + start + 1 && i == page)
            element = "<li class='active'><span>" + i + "</span></li>"
        else if (i == pages + start + 1)
            element = "<li class ='next' ><a href='/" + path + "/" + (parseInt(page)+1) + query +"'><i class='fas fa-chevron-right'></i></a></li>"
        $(".page").append(element);
    }
    if(page == numberOfPage){
        $(".page li:last-child a").addClass("disable");
        $(".page li:last-child a").removeAttr("href");
    }
    if (page == 1){
        $(".page li:first-child a").addClass("disable");
        $(".page li:first-child a").removeAttr("href");
    } 
}



