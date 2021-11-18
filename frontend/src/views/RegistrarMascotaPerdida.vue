<template>
    <v-container>
        <p class="mt-8 text-h5">Mascota Perdida</p>
        <v-row>
            <v-col>
                <v-card>
                    <v-card-text>
                        <v-textarea
                            label="Estado de la mascota cuando se la encontró"
                            outlined
                        ></v-textarea>
                        <v-file-input
                            label="Seleccionar imagenes"
                            outlined
                        ></v-file-input>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-card>
                    <v-card-text>
                        <p>¿Donde estaba la mascota cuando la encontró?</p>
                        <GmapMap
                            class="map"
                            :center="{lat:-34.6037345, lng:-58.3837591}"
                            :zoom="15"
                            map-type-id="terrain"
                            @click="clickMap"
                        >
                            <GmapMarker
                                :position="marker.position"
                            ></GmapMarker>
                        </GmapMap>
                        <div class="mt-4 d-flex justify-end">
                            <v-btn color="primary">Publicar</v-btn>
                        </div>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: "RegistrarMascotaPerdida.vue",
    data: () => ({
        marker: {
            position: {
                lat: -34.6037345,
                lng: -58.3837591
            }
        }
    }),
    methods: {
        clickMap(data) {
            let lat = data.latLng.lat();
            let lng = data.latLng.lng();

            this.marker.position.lat = lat;
            this.marker.position.lng = lng;
            console.log(lat, lng);
        }
    }
}
</script>

<style scoped>
.map {
    width: 100%;
    max-width: 100%;
    height: 400px;
}
</style>