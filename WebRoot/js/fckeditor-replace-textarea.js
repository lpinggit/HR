//使用fckeditor,把textarea的name='description'的文本域替换成富文本fckeditor
$().ready(function(){
	var oFCKeditor = new FCKeditor("skills") ;
	oFCKeditor.BasePath	= "fckeditor/" ;//根路径
	oFCKeditor.Height = "200";
	oFCKeditor.Width = "800";
	oFCKeditor.ToolbarSet = "bbs";
	oFCKeditor.ReplaceTextarea() ;

});