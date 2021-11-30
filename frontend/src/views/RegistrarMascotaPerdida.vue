<template>
    <v-container>
        <v-slide-x-transition mode="out-in">
            <SeleccionarOrganizacion
                v-if="organizacion_id === null"
                @OrganizacionSeleccionada="organizacionSeleccionada"
            />
            <div v-else>
                <p class="mt-8 text-h5">Mascota Perdida</p>
                <v-row>
                    <v-col>
                        <v-card :loading="loading">
                            <v-card-text>
                                <v-row>
                                    <v-col>
                                        <p class="mb-0">Especie</p>
                                        <v-radio-group
                                            row
                                            dense
                                            class="mt-0"
                                            hide-details
                                            v-model="especie"
                                        >
                                            <v-radio
                                                label="Gato"
                                                value="Gato"
                                            ></v-radio>
                                            <v-radio
                                                label="Perro"
                                                value="Perro"
                                            ></v-radio>
                                        </v-radio-group>
                                    </v-col>
                                    <v-col>
                                        <p class="mb-0">Tamaño</p>
                                        <v-radio-group
                                            row
                                            dense
                                            class="mt-0"
                                            hide-details
                                            v-model="tamanio"
                                        >
                                            <v-radio
                                                label="Grande"
                                                value="Grande"
                                            ></v-radio>
                                            <v-radio
                                                label="Mediana"
                                                value="Mediana"
                                            ></v-radio>
                                            <v-radio
                                                label="Chica"
                                                value="Chica"
                                            ></v-radio>
                                        </v-radio-group>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col>
                                        <v-textarea
                                            label="Estado de la mascota cuando se la encontró"
                                            outlined
                                            dense
                                            hide-details
                                            v-model="estadoMascota"
                                        ></v-textarea>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col>
                                        <v-textarea
                                            label="Caracteristicas"
                                            outlined
                                            dense
                                            v-model="caracteristicas"
                                        ></v-textarea>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col
                                        sm="6"
                                        md="4"
                                        lg="3"
                                        v-for="(foto, index) in fotos"
                                        :key="index"
                                    >
                                        <v-img :src="foto.imagenBase64" width="100%"></v-img>
                                    </v-col>
                                </v-row>
                                <v-file-input
                                    accept="image/png, image/jpeg"
                                    placeholder="Selecciona imagenes"
                                    prepend-icon="mdi-camera"
                                    label="Fotos"
                                    multiple
                                    small-chips
                                    outlined
                                    dense
                                    v-model="files"
                                    @change="updateFotos()"
                                ></v-file-input>
                            </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-card :loading="loading">
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
                            </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
                <div class="mt-4 d-flex justify-end">
                    <v-btn
                        color="primary"
                        @click="publicar"
                        :disabled="loading"
                    >
                        Publicar
                    </v-btn>
                </div>
            </div>
        </v-slide-x-transition>
    </v-container>
</template>

<script>
import SeleccionarOrganizacion from "@/components/SeleccionarOrganizacion";
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "RegistrarMascotaPerdida.vue",
    props: {
        persona_id: Number
    },
    components: {
        SeleccionarOrganizacion: SeleccionarOrganizacion
    },
    data: () => ({
        loading: true,
        exito: false,
        organizacion_id: null,
        marker: {
            position: {
                lat: -34.6037345,
                lng: -58.3837591
            }
        },
        especie: "Gato",
        tamanio: "Grande",
        estadoMascota: "",
        caracteristicas: "",
        files: [],
        fotos: [],
        categorias: []
    }),
    methods: {
        clickMap(data) {
            let lat = data.latLng.lat();
            let lng = data.latLng.lng();

            this.marker.position.lat = lat;
            this.marker.position.lng = lng;
            console.log(lat, lng);
        },
        organizacionSeleccionada(id) {
            this.organizacion_id = id;
        },
        getCategorias() {
            this.loading = true;

            let request = {
                url: "/api/publicacion/categoria",
                handler: {
                    "200": (response) => {
                        console.log(response.data);

                        let categorias = response.data;
                        this.categorias = categorias;
                    },
                    default: (response) => {
                        console.log("default", response.data)
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loading = false;
                    }
                }
            }

            RequestHelper.get(request);
        },
        updateFotos() {
            let files = this.files;
            let fotos = [];

            files.forEach(file => {
                this.getBase64(file)
                    .then(fileBase64 => {
                        fotos.push({
                            imagenBase64: fileBase64
                        })
                    })
            })

            this.fotos = fotos;
        },
        getBase64: function (file) {
            return new Promise((resolve, reject) => {
                let reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    resolve(reader.result)
                };
                reader.onerror = function (error) {
                    reject('Error: ', error);
                }
            })
        },
        publicar() {
            this.loading = true;

            console.log(this.persona_id);
            console.log(this.marker.position);
            console.log(this.fotos);

            let categoria = this.categorias.filter(categoria => categoria.nombre === "Mascota perdida")

            if(categoria.length > 0)
                categoria = categoria[0]
            else
                categoria = null

            let body = {
                organizacion_id: this.organizacion_id,
                categoria: categoria,
                autor_id: this.persona_id,
                titulo: `Encontre una mascota perdida`,
                cuerpo: `${this.estadoMascota}\n Ubicacion: \n latitud: ${this.marker.position.lat} \n longitud: ${this.marker.position.long}`,
                fotos: this.fotos
            }

            let request = {
                url: `/api/publicacion/normal`,
                body: body,
                handler: {
                    "201": () => {
                        this.exito = true;

                        let posicionGeografica = {
                            latitud: this.marker.position.lat,
                            longitud: this.marker.position.lng
                        }

                        let caracteristicas = this.caracteristicas.split("\n");

                        this.$router.push({
                            name: "hogares-transito",
                            params: {
                                tamanio: this.tamanio,
                                especie: this.especie,
                                caracteristicas: caracteristicas,
                                posicionGeografica: posicionGeografica,
                            }
                        })
                    },
                    default: (response) => {
                        console.log("default", response.data)
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loading = false;
                    }
                }
            }

            RequestHelper.post(request);
        }
    },
    created() {
        if(!this.persona_id) {
            this.$router.push({name: "registrar-rescatista-sin-chapita"});
            return;
        }

        this.getCategorias();
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