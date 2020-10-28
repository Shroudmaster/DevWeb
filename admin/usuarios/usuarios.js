$(() => {
    $('select.categoria').each((i,ele) => {
        targetId = "input[name=" + $(ele).data('connect') + "]";
        $(targetId).change((evt) => {
            if($(evt.target).val() == "Autor") {
                $(ele).prop("disabled", false);
            } else {
                $(ele).prop("disabled", true);
            }
        })
    })
})