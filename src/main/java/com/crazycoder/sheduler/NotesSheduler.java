package com.crazycoder.sheduler;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.crazycoder.model.Notes;
import com.crazycoder.repository.NotesRepository;

@Component
public class NotesSheduler {

   @Autowired
   private NotesRepository notesRepository;
   
	 @Scheduled(cron = "0 0 0 * * ?")
  // @Scheduled(cron = "* * * ? * *")
	public void deleteNotesSheduler() {
		
		LocalDateTime minusdays= LocalDateTime.now().minusDays(7);
		List<Notes> deleteNotes = notesRepository.findAllByIsDeletedAndDeletedOnBefore(true,minusdays);
		notesRepository.deleteAll(deleteNotes);
	}

}
