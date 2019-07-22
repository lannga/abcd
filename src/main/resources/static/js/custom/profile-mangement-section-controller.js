function getSection (section){
    $('.nav-menu > li').removeClass("bg-dark");
    $('.nav-menu > li > a').addClass("text-black");
    $('.section').addClass("d-none")

    switch (section) {
        case "posts":
            $('.posts').addClass('bg-dark');
            $('.posts > a').removeClass('text-black');           
            $('#posts').removeClass("d-none");
            break;
        case "your-posts": 
            $('.your-posts').addClass('bg-dark');
            $('.your-posts > a').removeClass('text-black');       
            $('#your-posts').removeClass("d-none");

            break;
        case "your-applications":
            $('.your-applications').addClass('bg-dark');
            $('.your-applications > a').removeClass('text-black'); 
            $('#your-applications').removeClass("d-none");            
            break;    
        default:
            break;
    }
}