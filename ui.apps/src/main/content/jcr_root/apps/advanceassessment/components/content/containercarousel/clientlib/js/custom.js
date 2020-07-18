$(document).ready(function(){ 
    let imagesArray = $('.item');
    for(let i = 0; i < imagesArray.length; i++ ) {
        console.log(imagesArray[i]);
    }
    $(imagesArray[0]).addClass("active");
    $(imagesArray[1]).addClass("next");
    $(imagesArray[imagesArray.length-1]).addClass("prev");
    const nextSlide = () => {
        let index;
        let nextIndex;
        let prevIndex;
        for(let i = 0; i < imagesArray.length; i++) {
            if ($(imagesArray[i]).hasClass("active")) {
                index = i;
            }
        }
        
        $(imagesArray[index]).removeClass("active");
        $(imagesArray[index]).addClass("prev");
        if((index+1) >= imagesArray.length) {
            nextIndex = 0
        }
        else {
            nextIndex = index+1;
        }
        
        $(imagesArray[nextIndex]).removeClass("next");
        $(imagesArray[nextIndex]).addClass("active");
        if((index-1) < 0) {
            prevIndex = imagesArray.length - 1;
        }
        else {
            prevIndex = index-1;
        }
        
        $(imagesArray[prevIndex]).removeClass("prev");
        let nextnextIndex;
        if((nextIndex+1) >= imagesArray.length) {
            nextnextIndex = 0;
        }
        else {
            nextnextIndex = nextIndex+1;
        }
        $(imagesArray[nextnextIndex]).addClass("next");
        
    }
    const prevSlide = () => {
        let index;
        let nextIndex;
        let prevIndex;
        let prevprevIndex;
        for(let i = 0; i < imagesArray.length; i++) {
            if ($(imagesArray[i]).hasClass("active")) {
                index = i;
            }
        }
        $(imagesArray[index]).removeClass("prev");
        $(imagesArray[index]).removeClass("active");
        $(imagesArray[index]).addClass("next");
        console.log("-------------------------------------------------------------");
        console.log(imagesArray[index]);
        if((index-1) < 0) {
            prevIndex = imagesArray.length - 1;
        }
        else {
            prevIndex = index-1;
        }
        $(imagesArray[index]).removeClass("next");
        $(imagesArray[prevIndex]).removeClass("prev");
        $(imagesArray[prevIndex]).addClass("active");
        console.log(imagesArray[prevIndex]);
        if((prevIndex-1) < 0) {
            prevprevIndex = imagesArray.length - 1;
        }
        else {
            prevprevIndex = prevIndex-1;
        }
        $(imagesArray[index]).removeClass("active");
        $(imagesArray[prevprevIndex]).removeClass("next");
        $(imagesArray[prevprevIndex]).addClass("prev");
        console.log(imagesArray[prevprevIndex]);
        console.log(prevprevIndex);
        
    }
    $('#rightSlide').click(() => {
        nextSlide();
    });
    $('#leftSlide').click(() => {
        prevSlide();
    });
})