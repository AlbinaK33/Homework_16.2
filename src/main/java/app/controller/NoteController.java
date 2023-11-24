package app.controller;

import app.entity.Note;
import app.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView listNotes() {
        Map<Long, Note> notes = noteService.listAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("notes", notes.values());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteNote(@PathVariable("id") long id) {
        noteService.deleteById(id);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/edit")
    public ModelAndView editNote(@RequestParam("id") long id) {
        Note note = noteService.getById(id);
        ModelAndView modelAndView = new ModelAndView("note/edit");
        modelAndView.addObject("notes", note);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView saveEditedNote(Note note) {
        noteService.update(note);
        return new ModelAndView("redirect:/note/list");
    }

}

