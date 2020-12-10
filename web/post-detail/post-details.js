$(() => {
    var toolbarOptions = [
        [{
            'header': [1, 2, 3, 4, 5, 6, false]
        }],
        ['bold', 'italic', 'underline', 'strike'], // toggled buttons
        ['blockquote', 'code-block'],

        [{
            'header': 1
        }, {
            'header': 2
        }], // custom button values
        [{
            'list': 'ordered'
        }, {
            'list': 'bullet'
        }],
        [{
            'script': 'sub'
        }, {
            'script': 'super'
        }], // superscript/subscript
        [{
            'indent': '-1'
        }, {
            'indent': '+1'
        }], // outdent/indent
        [{
            'direction': 'rtl'
        }], // text direction

        [{
            'size': ['small', false, 'large', 'huge']
        }], // custom dropdown

        [{
            'color': []
        }, {
            'background': []
        }], // dropdown with defaults from theme
        [{
            'font': []
        }],
        [{
            'align': []
        }],
        ['link', 'image'],

        ['clean'] // remove formatting button
    ];

    var quillFull = new Quill('#document-full', {
        modules: {
            toolbar: toolbarOptions,
            autoformat: true
        },
        theme: 'snow',
        placeholder: "Write something..."
    })
    
    $("#post-form").on("submit",function(){
        $("#hiddenArea").val($("#document-full").children().html());
    })
    
    $(".edit-com").on("click",function(e){
        e.preventDefault();
        var coment = $(this).parent().siblings(".conteudo-comentario").children().html();
        var id = $(this).parent().siblings(".id-comentario").val();
        $("#document-full").children().html(coment);
        $("#idComentario").val(id);
    })
})