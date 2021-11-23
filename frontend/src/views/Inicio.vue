<template>
    <v-container>
        <v-row class="mt-2">
            <v-col class="d-flex">
                <v-row>
                    <v-col>
                        <span class="text-h5">Publicaciones</span>
                    </v-col>
                    <v-col class="d-flex flex-column-reverse flex-sm-row justify-end">
                        <v-btn
                            text
                            color="primary"
                            class="mt-2 mt-sm-0 mr-sm-2"
                            @click="filtros.mostrar = filtros.mostrar === undefined ? 0 : undefined"
                        >
                            Filtros
                            <v-icon v-if="filtros.mostrar === 0" right>mdi-chevron-up</v-icon>
                            <v-icon v-else right>mdi-chevron-down</v-icon>
                        </v-btn>
                        <v-btn color="primary">
                            Crear publicacion
                            <v-icon right>mdi-plus</v-icon>
                        </v-btn>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
        <v-row class="my-0">
            <v-col>
                <v-expansion-panels
                    v-model="filtros.mostrar"
                    flat
                    popout
                >
                    <v-expansion-panel style="background-color: transparent">
                        <v-expansion-panel-content>
                            <v-row>
                                <v-col>
                                    <v-select
                                        outlined
                                        dense
                                        label="Categoria"
                                        :items="categorias"
                                        v-model="filtros.categoria"
                                        clearable
                                        hide-details
                                    >
                                    </v-select>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                        outlined
                                        dense
                                        label="Titulo"
                                        v-model="filtros.titulo"
                                        hide-details
                                    ></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                        outlined
                                        dense
                                        label="Cuerpo"
                                        v-model="filtros.cuerpo"
                                        hide-details
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                        </v-expansion-panel-content>
                    </v-expansion-panel>
                </v-expansion-panels>
            </v-col>
        </v-row>
        <v-row>
            <v-col
                class="col-12 col-sm-6 col-lg-4"
                v-for="publicacion in publicaciones_filtradas"
                :key="publicacion.id"
            >
                <v-card>
                    <v-card-subtitle class="pb-0">{{publicacion.categoria}}</v-card-subtitle>
                    <v-card-title class="pt-0">{{publicacion.titulo}}</v-card-title>
                    <v-card-text>{{publicacion.cuerpo}}</v-card-text>
                    <v-card-actions>
                        <v-btn text color="primary" small>Ver mas</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    data: () => ({
        filtros: {
            mostrar: undefined,
            categoria: "",
            titulo: "",
            cuerpo: ""
        },
        categorias: [
            {
                id: 1,
                value: "Quiero adoptar",
                text: "Quiero adoptar",
                descripcion: "Bla bla bla"
            }
        ],
        publicaciones: [
            {
                id: 1,
                titulo: "Lorem ipsum",
                cuerpo: "aelfjailfjealj fa kejlka elkaefaelkf aela efale",
                categoria: "Quiero adoptar",
                organizacion_id: 1,
                autor_id: 1
            },
            {
                id: 2,
                titulo: "Abcdefgh",
                cuerpo: "roiqwpoi pqo iropr iqop rqepoiqer poi",
                categoria: "Mascota en adopcion",
                organizacion_id: 1,
                autor_id: 1
            },
        ]
    }),
    computed: {
        publicaciones_filtradas() {
            if(!this.filtros.categoria && !this.filtros.titulo && !this.filtros.cuerpo)
                return this.publicaciones;

            return this.publicaciones.filter(
                publicacion =>
                    (!this.filtros.categoria || publicacion.categoria === this.filtros.categoria) &&
                    publicacion.titulo.toLowerCase().search(this.filtros.titulo.toLowerCase()) !== -1 &&
                    publicacion.cuerpo.toLowerCase().search(this.filtros.cuerpo.toLowerCase()) !== -1
            );
        }
    }
}
</script>

<style>
.v-expansion-panel-content__wrap {
    padding: 0 !important;
}
</style>