package com.galleria.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.galleria.data.Museum;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MuseumViewModel extends ViewModel {
    private String mockMuseum = "[" +
            "{id: 1, name: 'Louvre', museumId: 1, image: 'louvre', latitude: 48.861389, longitude: 2.335, description: 'Louvre ou Museu do Louvre (em francês: Musée du Louvre) é o maior museu de arte do mundo e um monumento histórico em Paris, França. Um marco central da cidade, está localizado na margem direita do rio Sena, no 1º arrondissement (distrito) da cidade. Aproximadamente 38 mil objetos, da pré-história ao século XXI, são exibidos em uma área de 72 735 metros quadrados.[1] Em 2019, o Louvre recebeu 9,6 milhões de visitantes, o que o torna o museu mais visitado do mundo.'}," +
            "{id: 2, name: 'Metropolitan Museum of Art', museumId: 2, image: 'metro', longitude: 40.779434, longitude: 74.6366666666666, description: 'O Metropolitan Museum of Art (em português: Museu Metropolitano de Arte), conhecido informalmente como The Met, é um museu de arte localizado na cidade de Nova Iorque, Estados Unidos, sendo um dos mais visitados museus do planeta.'}" +
            "]";

    private MutableLiveData<List<Museum>> listRecommended;
    private MutableLiveData<List<Museum>> listFiltered;

    public MuseumViewModel() {
        listRecommended = new MutableLiveData<>();
        listFiltered = new MutableLiveData<>();

        recommendedMuseum();
    }

    private void recommendedMuseum() {
        final Type listType = new TypeToken<ArrayList<Museum>>(){}.getType();
        final List<Museum> museums = new Gson().fromJson(mockMuseum, listType);
        listRecommended.setValue(museums);
    }

    public void filterMuseum(String text) {
        final Type listType = new TypeToken<ArrayList<Museum>>(){}.getType();
        final List<Museum> museums = new Gson().fromJson(mockMuseum, listType);
        listFiltered.setValue(museums.stream()
                .filter(museum -> museum.getName().toUpperCase().contains(text.toUpperCase()) || museum.getDescription().toUpperCase().contains(text.toUpperCase()))
                .collect(Collectors.toList()));
    }

    public MutableLiveData<List<Museum>> getListRecommended() {
        return listRecommended;
    }

    public MutableLiveData<List<Museum>> getListFiltered() {
        return listFiltered;
    }
}
