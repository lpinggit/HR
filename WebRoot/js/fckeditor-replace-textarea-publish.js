//ʹ��fckeditor,��textarea��name='description'���ı����滻�ɸ��ı�fckeditor
$().ready(function(){
	var oFCKeditor = new FCKeditor("jobRequest") ;
	oFCKeditor.BasePath	= "fckeditor/" ;//��·��
	oFCKeditor.Height = "200";
	oFCKeditor.Width = "800";
	oFCKeditor.ToolbarSet = "bbs";
	oFCKeditor.ReplaceTextarea() ;

});