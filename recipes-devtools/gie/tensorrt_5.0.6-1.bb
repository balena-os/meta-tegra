DESCRIPTION = "NVIDIA TensorRT (GPU Inference Engine) for deep learning"
HOMEPAGE = "http://developer.nvidia.com/tensorrt"
LICENSE = "Proprietary"

inherit nvidia_devnet_downloads

SRC_URI = "\
    ${NVIDIA_DEVNET_MIRROR}/libnvinfer5_${PV}+cuda10.0_arm64.deb;name=lib;subdir=tensorrt \
    ${NVIDIA_DEVNET_MIRROR}/libnvinfer-dev_${PV}+cuda10.0_arm64.deb;name=dev;subdir=tensorrt \
    ${NVIDIA_DEVNET_MIRROR}/libnvinfer-samples_${PV}+cuda10.0_all.deb;name=samples;subdir=tensorrt \
"
SRC_URI[lib.md5sum] = "6880ccc6d8a8dc3ef023d3b485c1c692"
SRC_URI[lib.sha256sum] = "b1e7a49a3df15ef0e7fdae785fcd95628ff7c3b05fce9796f68d4b41fbb55598"
SRC_URI[dev.md5sum] = "beb81ab9eba95a813c16b147af338021"
SRC_URI[dev.sha256sum] = "aa09fd4b587835896e64df87637380f7f3ddeb462ed49c97232a54ae30484223"
SRC_URI[samples.md5sum] = "fdaf46c4dfcd1cb1fd2c204037897959"
SRC_URI[samples.sha256sum] = "e285b63280212b22c3f3a8b7594bb8eb5702ed1f0b26bbb81d7642ca04b226ac"

COMPATIBLE_MACHINE = "(tegra)"
COMPATIBLE_MACHINE_tegra124 = "(-)"

LIC_FILES_CHKSUM = "file://usr/include/aarch64-linux-gnu/NvInfer.h;endline=48;md5=f38fd2aaeae3de115bacde66c2c93d2e"

CUDAPATH ?= "/usr/local/cuda-${CUDA_VERSION}"
BASEVER = "${@d.getVar('PV').split('-')[0]}"

S = "${WORKDIR}/tensorrt"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    install -d ${D}${includedir}
    install -m 0644 ${S}/usr/include/aarch64-linux-gnu/*.h ${D}${includedir}
    install -d ${D}${libdir}
    tar -C ${S}/usr/lib/aarch64-linux-gnu -cf- . | tar -C ${D}${libdir}/ --no-same-owner -xf-
    install -d ${D}${prefix}/src
    cp --preserve=mode,timestamps --recursive ${S}/usr/src/tensorrt ${D}${prefix}/src/
}
PACKAGES =+ "${PN}-samples"
FILES_${PN}-samples = "${prefix}/src"

RDEPENDS_${PN} += "libstdc++ cudnn cuda-cublas cuda-cudart cuda-command-line-tools-libnvtoolsext tegra-libraries libglvnd"
RDEPENDS_${PN}-samples += "bash python libglvnd"
RPROVIDES_${PN}-samples = "${PN}-examples"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
INSANE_SKIP_${PN} = "textrel ldflags"
INSANE_SKIP_${PN}-samples = "ldflags"
PACKAGE_ARCH = "${SOC_FAMILY_PKGARCH}"
