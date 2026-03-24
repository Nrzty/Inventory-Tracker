package com.example.demo.utils;

import com.example.demo.model.InventoryMovement;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvParser {

    private final List<InventoryMovement> movements = new ArrayList<>();

    public List<InventoryMovement> parse(MultipartFile file)
        throws IOException, CsvValidationException {
        try (
            CSVReader reader = new CSVReader(
                new InputStreamReader(file.getInputStream())
            )
        ) {
            reader.readNext();
            String[] linha;

            while ((linha = reader.readNext()) != null) {
                if (linha.length != 5) continue;

                boolean hasBlank = false;
                for (String campo : linha) {
                    if (campo.isBlank()) {
                        hasBlank = true;
                        break;
                    }
                }

                if (hasBlank) continue;

                long timestamp;
                try {
                    timestamp = Long.parseLong(linha[0]);
                } catch (NumberFormatException e) {
                    continue;
                }

                int quantity;
                try {
                    quantity = Integer.parseInt(linha[4]);
                    if (quantity <= 0) continue;
                } catch (NumberFormatException e) {
                    continue;
                }

                if (!linha[3].equals("in") && !linha[3].equals("out")) continue;

                InventoryMovement movement = new InventoryMovement();
                movement.setTimestamp(timestamp);
                movement.setProductId(linha[1]);
                movement.setProductName(linha[2]);
                movement.setType(linha[3]);
                movement.setQuantity(quantity);

                movements.add(movement);
            }
        }

        movements.sort(
            Comparator.comparingLong(InventoryMovement::getTimestamp)
        );
        return movements;
    }
}
