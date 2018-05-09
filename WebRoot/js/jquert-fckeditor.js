//fckeditor的创建
(function($){
	$.fckeditor = function(name){
		var oFCKeditor = new FCKeditor( name ) ;
		oFCKeditor.BasePath	= "fckeditor/" ;//根路径
		oFCKeditor.Height = "300";
		oFCKeditor.Width = "600";
		oFCKeditor.ToolbarSet = "bbs";
		oFCKeditor.ReplaceTextarea() ;
	}
})(jQuery);