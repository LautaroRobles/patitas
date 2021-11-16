<template>
    <v-container>
        <v-slide-x-transition mode="out-in">
            <SeleccionarOrganizacion
                v-if="organizacion_id === null"
                @OrganizacionSeleccionada="organizacionSeleccionada"
            />
            <div v-else>
                <p class="text-h5 mt-4">Registrar Mascota</p>
                <v-card class="mt-6"
                    :loading="loading"
                    :disabled="loading"
                    v-for="mascota in mascotas"
                    :key="mascota.key"
                >
                    <v-card-title>{{ mascota.nombre ? mascota.nombre : "Datos de Mascota" }}</v-card-title>
                    <v-card-text>
                        <v-row>
                            <v-col>
                                <p class="mb-0">Especie</p>
                                <v-radio-group
                                    row
                                    dense
                                    class="mt-0"
                                    hide-details
                                    v-model="mascota.especie"
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
                                <p class="mb-0">Sexo</p>
                                <v-radio-group
                                    row
                                    dense
                                    class="mt-0"
                                    hide-details
                                    v-model="mascota.sexo"
                                >
                                    <v-radio
                                        label="Macho"
                                        value="Macho"
                                    ></v-radio>
                                    <v-radio
                                        label="Hembra"
                                        value="Hembra"
                                    ></v-radio>
                                </v-radio-group>
                            </v-col>
                            <v-col>
                            </v-col>
                        </v-row>
                        <v-row class="mt-0">
                            <v-col class="col-12 col-sm-4">
                                <v-text-field
                                    label="Nombre"
                                    outlined
                                    dense
                                    v-model="mascota.nombre"
                                    hide-details
                                ></v-text-field>
                            </v-col>
                            <v-col>
                                <v-text-field
                                    label="Apodo"
                                    outlined
                                    dense
                                    v-model="mascota.apodo"
                                ></v-text-field>
                            </v-col>
                            <v-col>
                                <v-text-field
                                    label="Edad Aproximada"
                                    outlined
                                    dense
                                    v-model="mascota.edad"
                                ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-textarea
                            label="Descripción física"
                            outlined
                            dense
                            v-model="mascota.descripcion"
                        ></v-textarea>
                    </v-card-text>
                </v-card>
                <v-card class="mt-4"
                    :loading="loading"
                    :disabled="loading"
                >
                    <v-card-text class="d-flex justify-center">
                        <div>
                            <v-btn
                                color="primary"
                                outlined
                                class="mr-2"
                                @click="agregarMascota"
                            >
                                Agregar mascota
                            </v-btn>
                            <v-btn
                                color="primary"
                                @click="confirmarMascotas"
                            >
                                Confirmar
                            </v-btn>
                        </div>
                    </v-card-text>
                </v-card>
            </div>
        </v-slide-x-transition>
        <v-dialog
            v-model="exito"
            persistent
            max-width="900"
        >
            <v-card>
                <v-card-title>¡Registro exitoso!</v-card-title>
                <v-card-text>
                    <p>Sus mascotas fueron cargadas correctamente</p>
                    <v-row>
                        <v-col
                            v-for="mascota in mascotas_guardadas"
                            :key=mascota.id
                            class="d-flex flex-column align-center"
                        >
                            <p class="text-h5">{{ mascota.nombre }}</p>
                            <qr-code :text="`patitas.conlatoso.com/mascota/${mascota.id}/perdida`"></qr-code>
                        </v-col>
                    </v-row>
                </v-card-text>
                <v-card-actions>
                    <v-btn text color="primary" to="/">Ir al inicio</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-container>
</template>

<script>
import SeleccionarOrganizacion from "@/components/SeleccionarOrganizacion";
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "RegistrarMascota",
    props: {
        persona_id: Number
    },
    components: {
        SeleccionarOrganizacion: SeleccionarOrganizacion
    },
    data: () => ({
        loading: false,
        exito: false,
        organizacion_id: null,
        mascotas: [
            {
                key: 1,
                especie: "Gato",
                nombre: "",
                apodo: "",
                edad: "",
                sexo: "Macho",
                descripcion: "",
                caracteristicas: []
            }
        ],
        mascotas_guardadas: []
    }),
    methods: {
        confirmarMascotas() {
            this.loading = true;

            let body = {
                mascotas: this.mascotas
            }

            body.mascotas.forEach(mascota => {
                mascota.duenio_id = this.persona_id;
                mascota.organizacion_id = this.organizacion_id;
            });

            const request = {
                url: "/api/mascota/varias",
                body: body,
                handler: {
                    "201": (response) => {
                        this.mascotas_guardadas = response.data.mascotas;
                        console.log(this.mascotas_guardadas);
                        this.exito = true;
                    },
                    error: (response) => {
                        console.log("error", response.data);
                    },
                    always: () => {
                        this.loading = false;
                    }
                }
            };
            RequestHelper.post(request);
        },
        organizacionSeleccionada(id) {
            this.organizacion_id = id;
        },
        agregarMascota() {
            let nuevaMascota = {
                key: 1,
                especie: "Gato",
                nombre: "",
                apodo: "",
                edad: "",
                sexo: "Macho",
                descripcion: "",
                caracteristicas: []
            }

            if(this.mascotas.length >= 1)
                nuevaMascota.key = this.mascotas[this.mascotas.length - 1].key + 1;

            this.mascotas.push(nuevaMascota);
        }
    },
    created() {
        if(!this.persona_id) {
            this.$router.push({name: "registrar-persona"});
        }
    }
}
</script>

<style scoped>

</style>