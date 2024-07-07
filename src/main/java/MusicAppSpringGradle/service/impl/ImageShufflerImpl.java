package MusicAppSpringGradle.service.impl;

import MusicAppSpringGradle.service.ImageShuffler;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ImageShufflerImpl implements ImageShuffler {
    @Override
    public void shuffle(List<String> images) {
        Collections.shuffle(images);

    }
}
