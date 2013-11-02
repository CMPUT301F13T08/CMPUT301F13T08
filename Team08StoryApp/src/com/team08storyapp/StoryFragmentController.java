package com.team08storyapp;




public class StoryFragmentController {
	
	public void addAnnotation(Annotation annotation, StoryFragment storyfragment){
        storyfragment.getAnnotations().add(annotation);
	}
	
	public void addChoice(Choice choice, StoryFragment storyfragment){
		storyfragment.getChoices().add(choice);
	}
	
	public void addImage (Photo photo, StoryFragment storyfragment){
		storyfragment.getPhotos().add(photo);
	}
	
	public boolean updateStoryFragment(StoryFragment storyfragment){
		// TODO Auto-generated method stub
		return false;
	}

}
