package app.service;

import app.entity.Note;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoteService {

    private Map<Long, Note> noteMap = new HashMap<>();

    public Map<Long, Note> listAll() {
        return noteMap;
    }


    @PostConstruct
    public void init() {
        addTestNotes();
    }

    public void addTestNotes() {
        noteMap.put(1L, new Note(1L, "Note 1", "Content 1"));
        noteMap.put(2L, new Note(2L, "Note 2", "Content 2"));
        noteMap.put(3L, new Note(3L, "Note 3", "Content 3"));
    }

    public Note add(Note note) {
        Long id = generateId();
        note.setId(id);
        noteMap.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (!noteMap.containsKey(id)) {
            throw new RuntimeException("Note with id " + id + " not found");
        }
        noteMap.remove(id);
    }

    public void update(Note note) {
        if (!noteMap.containsKey(note.getId())) {
            throw new RuntimeException("Note with id " + note.getId() + " not found");
        }
        noteMap.put(note.getId(), note);
    }

    public Note getById(long id) {
        if (!noteMap.containsKey(id)) {
            throw new RuntimeException("Note with id " + id + " not found");
        }
        return noteMap.get(id);
    }

    private Long generateId() {
        return Math.round(Math.random() * 1000000);
    }
}


