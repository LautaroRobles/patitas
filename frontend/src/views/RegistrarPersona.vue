<template>
    <v-container>
        <h2>Registrar Persona</h2>
        <v-card class="mt-2">
            <v-card-title>Datos de Persona</v-card-title>
            <v-card-text>
                <v-row>
                    <v-col>
                        <v-text-field
                            label="Nombre"
                            outlined
                            dense
                            v-model="datosDePersona.nombre"
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            label="Apellido"
                            outlined
                            dense
                            v-model="datosDePersona.apellido"
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-text-field
                    label="Fecha de Nacimiento"
                    outlined
                    dense
                    v-model="datosDePersona.fechaDeNacimiento"
                ></v-text-field>
                <v-row>
                    <v-col class="col-3">
                        <v-select
                            label="Tipo de Documento"
                            :items="tiposDeDocumento"
                            v-model="datosDePersona.tipoDeDocumento"
                            outlined
                            dense
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-text-field
                            label="Numero de Documento"
                            outlined
                            dense
                            v-model="datosDePersona.nroDocumento"
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-text-field
                    label="Direccion"
                    outlined
                    dense
                    v-model="datosDePersona.direccion"
                ></v-text-field>
                <v-text-field
                    label="Telefono"
                    outlined
                    dense
                    v-model="datosDePersona.telefono"
                ></v-text-field>
                <v-text-field
                    label="Email"
                    outlined
                    dense
                    v-model="datosDePersona.email"
                ></v-text-field>
            </v-card-text>
        </v-card>
        <v-card class="mt-4">
            <v-card-title>Datos de Contacto</v-card-title>
            <v-card-text>
                <v-row>
                    <template v-for="contacto in datosDeContacto">
                        <v-col class="col-12 col-sm-6 col-md-4" :key="contacto.key">
                            <v-card outlined>
                                <v-card-text>
                                    <v-text-field
                                        class="mb-2"
                                        label="Nombre"
                                        outlined
                                        dense
                                        hide-details
                                        v-model="contacto.nombre"
                                    ></v-text-field>
                                    <v-text-field
                                        class="mb-2"
                                        label="Apellido"
                                        outlined
                                        dense
                                        hide-details
                                        v-model="contacto.apellido"
                                    ></v-text-field>
                                    <v-text-field
                                        class="mb-2"
                                        label="Telefono"
                                        outlined
                                        dense
                                        hide-details
                                        v-model="contacto.telefono"
                                    ></v-text-field>
                                    <v-text-field
                                        class="mb-2"
                                        label="Email"
                                        outlined
                                        dense
                                        hide-details
                                        v-model="contacto.email"
                                    ></v-text-field>
                                    <b>Formas de notificación preferidas</b>
                                    <v-row no-gutters>
                                        <v-col>
                                            <v-checkbox
                                                label="Whatsapp"
                                                value="WHATSAPP"
                                                hide-details
                                                dense
                                                v-model="contacto.tipoNotificaciones"
                                            ></v-checkbox>
                                        </v-col>
                                        <v-col>
                                            <v-checkbox
                                                label="SMS"
                                                value="SMS"
                                                hide-details
                                                dense
                                                v-model="contacto.tipoNotificaciones"
                                            ></v-checkbox>
                                        </v-col>
                                        <v-col>
                                            <v-checkbox
                                                label="Email"
                                                value="EMAIL"
                                                hide-details
                                                dense
                                                v-model="contacto.tipoNotificaciones"
                                            ></v-checkbox>
                                        </v-col>
                                    </v-row>
                                </v-card-text>
                                <v-card-actions>
                                    <v-btn
                                        text
                                        block
                                        color="primary"
                                        @click="borrarContacto(contacto.key)"
                                    >
                                        <v-icon left>
                                            mdi-delete
                                        </v-icon>
                                        Borrar contacto
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-col>
                    </template>
                    <v-col class="col-12 col-sm-6 col-md-4">
                        <v-btn
                            class="d-flex"
                            style="height: 100%; min-height: 200px"
                            color="primary"
                            outlined
                            block
                            @click="crearContacto"
                        >Agregar contacto</v-btn>
                    </v-col>
                </v-row>
            </v-card-text>
        </v-card>
        <div class="mt-4 d-flex justify-end">
            <v-btn
                color="primary"
                @click="confirmarPersona"
            >
                Siguiente
            </v-btn>
        </div>
    </v-container>
</template>

<script>
export default {
    name: "RegistrarPersona",
    data: () => ({
        loading: false,
        tiposDeDocumento: [
            "DNI",
            "Libreta",
            "No"
        ],
        datosDePersona: {
            nombre: "",
            apellido: "",
            fechaDeNacimiento: "",
            tipoDeDocumento: "",
            nroDocumento: "",
            direccion: "",
            telefono: "",
            email: ""
        },
        datosDeContacto: [
            {
                key: 0,
                nombre: "",
                apellido: "",
                telefono: "",
                email: "",
                tipoNotificaciones: []
            }
        ]
    }),
    methods: {
        crearContacto() {
            let lastKey = 0;
            if(this.datosDeContacto.length !== 0) {
                lastKey = this.datosDeContacto[this.datosDeContacto.length - 1].key + 1;
            }

            this.datosDeContacto.push({
                key: lastKey,
                nombre: "",
                apellido: "",
                telefono: "",
                email: "",
                tipoNotificaciones: []
            });
        },
        borrarContacto(key) {
            console.log(key);

            let contactoIndex = null;
            for(let i = 0; i < this.datosDeContacto.length; i++) {
                if(this.datosDeContacto[i].key === key) {
                    contactoIndex = i;
                }
            }

            if(contactoIndex != null)
                this.datosDeContacto.splice(contactoIndex, 1);
        },
        confirmarPersona() {

        }
    }
}
</script>

<style scoped>

</style>