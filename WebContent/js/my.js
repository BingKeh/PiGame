var CurPage = window.location.hash;
var CurPosition = 0;
var CurLength = 0;
var childListening = false;
var articles;


function read_checked(checkbox) {
    var btn = document.getElementById('sub');
    if (checkbox.checked) {
        btn.removeAttribute('disabled');
        btn.setAttribute('style', 'background: #00bee7;');
    } else {
        btn.setAttribute('disabled', 'true');
        btn.setAttribute('style', 'background: #f9f9f9;');
    }

    console.log(btn);
}


window.onhashchange = test;

function test() {
    detectPosition();
    $('.space_nav_cours').css('margin-left', CurPosition + 'px');
    $('.space_nav_cours').css('width', CurLength - 20 + 'px');
    console.log("hash changed!");
}

function buy_skin(cost) {
    $.post('do_login', { action: 'buy', 'cost': cost }, function(data) {
        if (data.result == 'ok') {
            alert("恭喜你购买成功!");
        } else {
            alert("购买失败!");
        }
    });
}

function black_room(black_href) {
    var index = black_href.parent().parent().attr('id').split('_')[1] - 2;
    var work_id = articles[index].iduser_work;
    console.log('The work id is : ' + articles[index].iduser_work);
    $.post('submit', { action: 'black_room', 'work_id': work_id }, function(data) {
        console.log(data.result);
    })
}


function detectPosition() {
    CurPage = window.location.hash;
    console.log(CurPage);
    switch (CurPage.substr(3, 1)) {
        case 'i':
            console.log("case i");
            CurPosition = $('.space_banner a:eq(0)').position().left;
            CurLength = $('.space_banner a:eq(0)').width();
            break;
        case 'w':
            CurPosition = $('.space_banner a:eq(1)').position().left;
            CurLength = $('.space_banner a:eq(1)').width();
            break;
        case 'f':
            CurPosition = $('.space_banner a:eq(2)').position().left;
            CurLength = $('.space_banner a:eq(2)').width();
            break;
        case 'c':
            CurPosition = $('.space_banner a:eq(3)').position().left;
            CurLength = $('.space_banner a:eq(3)').width();
            break;
        case 's':
            CurPosition = $('.space_banner a:eq(4)').position().left;
            CurLength = $('.space_banner a:eq(4)').width();
            break;
        default:
            CurPosition = $('.space_banner a:eq(0)').position().left;
            CurLength = $('.space_banner a:eq(0)').width();
    }
}


function richtextTool(action) {
    var editor = myframe.window.editor;

    switch (action) {
        case "makebold":
            editor.hasFormat('b') ? editor.removeBold() : editor.bold();
            break;
        case "makeitalic":
            editor.hasFormat('i') ? editor.removeItalic() : editor.italic();
            break;
        case "makeunderline":
            editor.hasFormat('u') ? editor.removeUnderline() : editor.underline();
            break;
        case "selectfont":
            if (childListening) {
                return;
            }
            $('.drop_down').eq(0).css('visibility') == "visible" ? $('.drop_down').eq(0).css('visibility', 'hidden')
                : $('.drop_down').eq(0).css('visibility', 'visible');
            return true;
        case "makelink":
            if (childListening) {
                return;
            }
            $('.drop_down').eq(1).css('visibility') == "visible" ? $('.drop_down').eq(1).css('visibility', 'hidden')
                : $('.drop_down').eq(1).css('visibility', 'visible');
            return true;
        case "makeorderlist":
            editor.hasFormat('li') ? editor.removeList() : editor.makeOrderedList();
            break;
        case "insertimage":
            if (childListening) {
                return;
            }
            $('.drop_down').eq(2).css('visibility') == "visible" ? $('.drop_down').eq(2).css('visibility', 'hidden')
                : $('.drop_down').eq(2).css('visibility', 'visible');
            return true;
            break;
        case "increaseQuoteLevel":
            editor.increaseQuoteLevel()
            break;
        case "makeHeading":
            editor.setFontSize(38);
            break;
        case "alignLeft":
            editor.setTextAlignment('left');
            break;
        case "alignCenter":
            editor.setTextAlignment('center');
            break;
        case "alignRight":
            editor.setTextAlignment('right');
            break;
        case "undo":
            editor.undo();
            break;
        case "redo":
            editor.redo();
            break;
    }
}

function changeFont() {
    var editor = myframe.window.editor;
    var select_size = $('#select_size');
    var select_face = $('#select_face');
    fontSize = select_size.get(0)[parseInt(select_size.get(0).selectedIndex)].dataset.size;
    fontFace = select_face.get(0)[parseInt(select_face.get(0).selectedIndex)].dataset.face;
    console.log("The size is " + fontSize + " and the face is " + fontFace);

    editor.setFontSize(parseInt(fontSize));
    editor.setFontFace(fontFace);
}

function makeLink(url) {
    var editor = myframe.window.editor;
    editor.makeLink(url);
}

function makeImg(url) {
    var editor = myframe.window.editor;
    editor.insertImage(url);
}

function publishArticle() {
    var editor = myframe.window.editor;
    var text = encodeURI(editor.getHTML());
    var base64 = $('#cover').attr('src');
    $.post('submit', { action: 'publish', data: text, heading: $('#cover_heading').val(), cover: base64 }, function (data) {
        console.log(data.status);
        alert(data.status);
        location.reload(true);
    });
}

function getArticle(role) {
    $.post('submit', { action: 'get_article', type: 'ARTICLE' , 'role': role}, function (data) {
        articles = data;
        showArticle(articles);
    });

}

function getComment() {
    var work_id = $("input[name='work_id']").val();

    $.post('comment.do', { action: 'get_comment', 'work_id': work_id }, function (data) {
        var item  = $('.comment_item').last();
        var realitem = item;
        var cLen = $('.comment_item').length;
        
        $.each(data, function(i, n) {
            var comment_item = item.clone(true).attr('id', 'comment-' + i);
            comment_item.css('visibility', 'visible');
            var avatar = comment_item.children().eq(0).children().eq(0);
            var comment_user = comment_item.children().eq(1).children().eq(0);
            var comment = comment_item.children().eq(1).children().eq(1);
            comment.text(n.comment);
            comment_user.text(n.user_nickname);
            var avatar_src = 'img/avatar/' + n.user_id + '-avatar.png';
            avatar.attr('src', avatar_src);
            item.after(comment_item);
            item = comment_item;
        });
        for (var i = 0; i < cLen; i++) {
            $('.comment_item').eq(0).remove();
        }
        //realitem.remove();
        console.log(data);
    });
}

function do_comment() {
    var work_id = $("input[name='work_id']").val();
    var comment = $('#my_comment').val();
    var user_id = $("input[name='user_id']").val();
    $.post('comment.do', { action: 'do_comment', 'work_id': work_id, 'comment': comment, 'user_id': user_id }, function (data) {
        console.log(data);
        getComment();
    })
}

function showArticle(articles) {
    var item = $('.article_card').eq(0);
    var realitem = item;
    count = 2;
    $.each(articles, function (i, n) {
        var newitem = item.clone(true).attr({ 'id': 'card_' + count });
        newitem.css('visibility', 'visible');
        var avatar = newitem.children().eq(0).children().eq(0).attr('src', n.user_id);
        var card_header = newitem.children().eq(1).children().eq(0).children();
        var card_brief = newitem.children().eq(1).children().eq(1);
        card_header.eq(1).text(n.work_heading);
        card_header.eq(0).children().eq(0).attr('src', n.work_cover);
        card_header.parent().attr('href', 'article.do?url=' + n.work_url + "&work_id=" + n.iduser_work);
        card_brief.text('这是一个简介');
        item.after(newitem);
        realitem.remove();
        item = newitem;
        console.log(n.work_heading);
        count++;
    });
}

function checkImg(input) {
    var reader = new FileReader();
    var file = $('#cover_img').get(0).files[0];
    var img = $('#cover');
    console.log("onchanged!");
    reader.onload = function (e) { img.attr('src', e.target.result); }
    reader.readAsDataURL(file);
}


$(document).ready(function () {
    var url = window.location.href;
    url = url.substr(url.lastIndexOf('/'), url.length);
    console.log(url);
    if (url.includes('space')) {
        $('.space_banner a').hover(function () {
            $('.space_nav_cours').css('margin-left', $(this).position().left + 'px');
            $('.space_nav_cours').css('width', $(this).width() - 20 + 'px');
            console.log($(this).width());
        },
            function () {
                detectPosition();
                $('.space_nav_cours').css('margin-left', CurPosition + 'px');
                $('.space_nav_cours').css('width', CurLength - 20 + 'px');
            });

        $('.space_banner a').click(function () {
            detectPosition();
            $('.space_nav_cours').css('margin-left', $(this).position().left + 'px');
            $('.space_nav_cours').css('width', $(this).width() - 20 + 'px');
        });

        detectPosition();
        $('.space_nav_cours').css('margin-left', CurPosition + 'px');
        $('.space_nav_cours').css('width', CurLength - 20 + 'px');
        console.log("The CurPosition is " + CurPosition);
    }

})

function delayJump(count) {
    window.setTimeout(function () {
        count--;
        if (count > 0) {
            $('.complete_upload').css(
                'visibility',
                'visible'
            )
            $('.complete_upload .sec').text(count);
            delayJump(count);
        } else {
            window.location.href = "space";
        }
    }, 1000);
}

function do_upload() {
    console.log("Hello Wolrd!");

    $('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            console.log(data.result.status);
            delayJump(3);
        },
        progressall: function (e, data) {
            $('.progress_div').css('visibility', 'visible');
            var width = 500;
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('.progress_mask').css(
                'width',
                progress * width / 100 + 'px'
            )
            console.log("The progress is " + progress);
        }
    });


    var myframe = document.getElementsByClassName('richtext')[0];


    myframe.addEventListener('load', function () {
        // Make sure we're in standards mode.
        var doc = myframe.contentDocument;
        // if ( doc.compatMode !== 'CSS1Compat' ) {
        //     doc.open();
        //     doc.write( '<!DOCTYPE html><title></title>' );
        //     doc.close();
        // }
        // doc.close() can cause a re-entrant load event in some browsers,
        // such as IE9.
        if (myframe.contentWindow.editor) {
            return;
        }
        myframe.contentWindow.editor = new Squire(myframe.contentWindow.document);
        myframe.contentWindow.editor.addStyles(
            'html {' +
            '  height: 100%;' +
            '}' +
            'body {' +
            '  -moz-box-sizing: border-box;' +
            '  -webkit-box-sizing: border-box;' +
            '  box-sizing: border-box;' +
            '  height: 100%;' +
            '  padding: 1em;' +
            '  background: transparent;' +
            '  color: #2b2b2b;' +
            '  font: 13px/1.35 Helvetica, arial, sans-serif;' +
            '  cursor: text;' +
            '}' +
            'a {' +
            '  text-decoration: underline;' +
            '}' +
            'h1 {' +
            '  font-size: 138.5%;' +
            '}' +
            'h2 {' +
            '  font-size: 123.1%;' +
            '}' +
            'h3 {' +
            '  font-size: 108%;' +
            '}' +
            'h1,h2,h3,p {' +
            '  margin: 1em 0;' +
            '}' +
            'h4,h5,h6 {' +
            '  margin: 0;' +
            '}' +
            'ul, ol {' +
            '  margin: 0 1em;' +
            '  padding: 0 1em;' +
            '}' +
            'blockquote {' +
            '  border-left: 2px solid blue;' +
            '  margin: 0;' +
            '  padding: 0 10px;' +
            '}'
        );
    });


}









