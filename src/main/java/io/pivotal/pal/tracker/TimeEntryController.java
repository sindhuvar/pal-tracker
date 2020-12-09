package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(final TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody final TimeEntry timeEntry) {
        TimeEntry timeEntryResposne = timeEntryRepository.create(timeEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(timeEntryResposne);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable final Long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        return timeEntry != null ? ResponseEntity.status(HttpStatus.OK).body(timeEntry) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = timeEntryRepository.list();
        return ResponseEntity.status(HttpStatus.OK).body(timeEntries);
    }

    @PutMapping ("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable final Long id, @RequestBody final TimeEntry timeEntry) {
        TimeEntry timeEntryResponse = timeEntryRepository.update(id, timeEntry);
        return timeEntryResponse != null ? ResponseEntity.status(HttpStatus.OK).body(timeEntryResponse) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping ("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable final Long id) {
        timeEntryRepository.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
