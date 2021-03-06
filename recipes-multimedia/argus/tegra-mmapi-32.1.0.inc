HOMEPAGE = "http://developer.nvidia.com"
LICENSE = "Proprietary & BSD"

SRC_URI = "${L4T_URI_BASE}/${L4T_BSP_BASEPFX}-Tegra_Multimedia_API_R${PV}_aarch64.tbz2"
SRC_MD5SUM = "bf7225ab1f3373328c0def240bc6cec2"
SRC_SHA256SUM = "905908bd4797efeed954b9cfc78eece0e1ad6c686723fe863f1852956553f012"
SRC_MD5SUM_tegra210 = "00979fd8a30ae5d49152d229465614e9"
SRC_SHA256SUM_tegra210 = "8f317f30e4a7f71c6655a821ca86b2a717c0cd9b3136a017fcc361cc35b4d89c"
SRC_URI[md5sum] = "${SRC_MD5SUM}"
SRC_URI[sha256sum] = "${SRC_SHA256SUM}"

inherit l4t_bsp

LIC_FILES_CHKSUM = "file://LICENSE;md5=2cc00be68c1227a7c42ff3620ef75d05 \
		    file://argus/LICENSE.TXT;md5=271791ce6ff6f928d44a848145021687"

S = "${WORKDIR}/tegra_multimedia_api"
B = "${S}"

COMPATIBLE_MACHINE = "(tegra)"
COMPATIBLE_MACHINE_tegra124 = "(-)"

PACKAGE_ARCH = "${SOC_FAMILY_PKGARCH}"

